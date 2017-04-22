__author__ = "Thiago Carreira A. Nascimento"
# automatic
# -*- coding: utf-8 -*-
from utils.Mapings import Mapeamento

class Menu:

    def getadministrador(self):
        objeto = Mapeamento("")
        return objeto.getlinktextmap()

    def getanalistas(self):
        objeto = Mapeamento("")
        return objeto.getlinktextmap()

    def getidealizadores(self):
        objeto = Mapeamento("")
        return objeto.getlinktextmap()

    def getideias(self):
        objeto = Mapeamento("")
        return objeto.getlinktextmap()

    def getmenuconfiguracoes(self):
        objeto = Mapeamento("") #javascript: void(0);
        return objeto.getlinkpartialmap()