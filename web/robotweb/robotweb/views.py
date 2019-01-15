from django.shortcuts import render, HttpResponse

def home(request):
    # context = {
    #     'ref' : "PageRank"
    # }
    return render(request, 'home.html')
