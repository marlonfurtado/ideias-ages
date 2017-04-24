# -*- coding: utf-8
import os
from ObjectsMap import LoginObjectMap
from selenium import webdriver


class LoginTestCase: #a apenas rascunho

    def setUp(self):
        self.browser = webdriver.PhantomJS
        self.addCleanup(self.browser.quit)

    def getDriver(self):
        if os.name == 'nt':
            return os.getcwd() + "\\phantomjs.exe"
        else:
            return os.getcwd() + "/phantomjs"

    def getLandPage(self):
        self.browser.get("http://ideias9269.cloudapp.net/login.jsp")

    def setLogin(self, login, password):
        sl = LoginObjectMap.LoginObjectMap()
        sl.getlogintextfieldobject().send_keys(login)
        sl.getpasstextfieldobject().send_keys(password)
        sl.getconfirmbuttonobject().click()

    def getScreenshot(self):
        evidence = self.getScreenshot()
        saving = open("evidencialogin.png", "r")
        saving.write(evidence)
        saving.close()


