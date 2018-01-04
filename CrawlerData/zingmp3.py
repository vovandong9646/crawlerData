from selenium import webdriver
# from bs4 import BeautifulSoup
# from xlwt import Workbook

driver = webdriver.PhantomJS(executable_path = r'C:\Users\DONGVO\Downloads\Compressed\phantomjs-2.1.1-windows\bin\phantomjs.exe')

driver.get('https://www.udemy.com/courses/development/web-development/all-courses/')

html_doc = driver.page_source

# soup = BeautifulSoup(html_doc,"lxml")

print(html_doc)

# list_items = soup.find("ul",{"id":"list-item"})

# wb = Workbook()

# sheetZing = wb.add_sheet("zingMp3")

# sheetZing.write(0,0,"Tên bài hát")
# sheetZing.write(0,1,"ảnh")
# sheetZing.write(0,2,"link")

# #a_items = list_items.find_all("a",class_="avatar-song")

# #tao ra 1 class
# class zingmp3():
# 	"""docstring for zingmp3"""
# 	def __init__(self):
# 		self.title = ""
# 		self.link = ""
# 		self.image = ""
		


# div_items = list_items.find_all("div",class_="item-rc ")

# list_zingMp3 = []

# for div_item in div_items:
# 	div_img = div_item.select("a.avatar-song > img")
# 	img = div_img[0]['src']
	
# 	div_title_link = div_item.select("div.desc-song > p.title > a")
# 	link = div_title_link[0]['href']
# 	title = div_title_link[0].string

# 	zing_mp3 = zingmp3()
# 	zing_mp3.image = img
# 	zing_mp3.link = link
# 	zing_mp3.title = title.strip()
# 	list_zingMp3.append(zing_mp3)

# row = 1
# for data in list_zingMp3:
# 	print("----------")
# 	print(data.link)
# 	print(data.title)
# 	print(data.image)
# 	print("----------\n")
# 	sheetZing.write(row,0,data.title)
# 	sheetZing.write(row,1,data.image)
# 	sheetZing.write(row,2,data.link)
# 	row = row + 1


# wb.save("ZingData1.xls")

# # for div_item in div_items:
# # 	div_item.a['class'] = 'avatar-song'
# # 	img = div_item.a.img['src']

# # 	div_item.div['class'] = 'desc-song'
# # 	link = div_item.a['href']
# # 	title = div_item.a
# # 	print(title)


driver.quit()