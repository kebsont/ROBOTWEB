from django.shortcuts import render, HttpResponse
from django.views.decorators.csrf import csrf_exempt
from .forms import NameForm
import urlparse

import socket
import time
import collections

# Fonction pour communiquer avec le serveur Java en Socket
links = ""
d = {}
od = {}
def sendToJava(host, port, mesg):
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sock.connect((host, port))

    sock.send(mesg+"\n")
    global links
    data = sock.recv(1024)
    print "1)-->", data

    if ( data == "Bienvenue\n" ):
        # Stock data in links var
        global links
        links = sock.recv(1024)

        # Verifier si l'url retourne d'autres urls, si oui, c bn sinon on affiche le msg derreur dans la page web
        # if links[0] == "":
        print"(***************************)"
        print(links)
        print("THE LINKS BEFORE")
        links = links[:-2]
        print("THE LINKS AFTER")
        print(links[:-2])
        print"(***************************)"


        # Wait for links ...
        print links

        sock.close()
        print "Socket closed"
    else:
        print("NOBIENVENUE")


@csrf_exempt
def home(request):
    # context = {
    #     'ref' : "PageRank"
    # }
    # print(request.GET)
    if 'srch-term' in request.POST:
        form = NameForm(request.POST)
        message = 'You searched for: %r' % request.POST['srch-term']
        searchTerm = request.POST['srch-term']
        print(searchTerm)
        sendToJava("127.0.0.1" , 1445, searchTerm)
        print(form.is_valid())
        # Test
        l_link = links[:-2]
        global od
        d = dict(item.split(",") for item in l_link.split(";"))
        od = collections.OrderedDict(sorted(d.items()))
        print("Vocii le dict: ")
        print(d)
        # Fin Test
    else:
        message = 'You submitted an empty form.'
        print(message)


    return render(request, 'home.html', {
        'links': od,
    })
