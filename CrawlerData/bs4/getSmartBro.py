from bs4 import BeautifulSoup
import requests
import csv
import pandas as pd

# url, req, soup

# url="https://udemycoupon.learnviral.com/page/2"

print('Input maxPage:')
maxPage = input()

result=[]

for pageIndex in range(int(maxPage)):
    url="https://smartybro.com/category/udemy-free-courses/page/" + str(pageIndex)
    response=requests.get(url)
    soup=BeautifulSoup(response.text, "html.parser")

    # file=csv.writer(open("out.csv", "w"))
    urlSmartyBro=[]
    for title in soup.find_all("h2", {'class': 'grid-tit'}):
        urlSmartyBro.append(title.findNext("a")['href'])

    #for url ra de get dc link udemy
    for urlSmart in urlSmartyBro:
        res=requests.get(urlSmart)
        soup=BeautifulSoup(res.text, "html.parser")
        print(soup.find("a", {'class': 'fasc-button fasc-size-xlarge fasc-type-flat'})['href'])
        result.append(soup.find("a", {'class': 'fasc-button fasc-size-xlarge fasc-type-flat'})['href'])


df = pd.DataFrame(result)
df.to_excel('./smartbro.xlsx', sheet_name='Course', index=False, header=False)
