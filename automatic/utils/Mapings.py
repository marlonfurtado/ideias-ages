from selenium import webdriver

__author__ = "Thiago Carreira A. Nascimento"
# phantom
# -*- coding: utf-8 -*-

driver = webdriver.PhantomJS()


class Mapeamento:

    def __init__(self, el):
        self.el = el

    def getnamemap(self):
        return driver.find_element_by_name(self.el)

    def getxpathmap(self):
        return driver.find_element_by_xpath(self.el)

    def getidmap(self):
        return driver.find_element_by_id(self.el)

    def getclassmap(self):
        return driver.find_element_by_class_name(self.el)

    def getlinktextmap(self):
        return driver.find_element_by_link_text(self.el)

    def getlinkpartialmap(self):
        return driver.find_element_by_partial_link_text(self.el)
