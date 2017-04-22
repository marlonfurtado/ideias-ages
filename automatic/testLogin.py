# -*- coding: utf-8

import os
from selenium import webdriver

class LoginTestCase(unittest.TestCase):

	def setUp(self):
		self.browser = webdriver.Firefox()
		self.addCleanup(self.browser.quit)
	
	def getDriver(self):
		if os.name == 'nt':
			return os.getcwd() + "\\chromedriver.exe"
		else:
			return os.getcwd() + "/chromedriver"

	def getLandPage(self):
		self.browser.get("http://index.html")
		

if __name__=='__main__'
	unittest.main(verbosity=2)
