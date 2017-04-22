from utils.Mapings import Mapeamento

__author__ = "Thiago Carreira A. Nascimento"
# -*- coding: utf-8 -*-


class LoginObjectMap:

    def getlogintextfieldobject(self):  # cpf
        objeto = Mapeamento("cpf")
        return objeto.getidmap()

    def getpasstextfieldobject(self):  # senha
        objeto = Mapeamento("password")
        return objeto.getidmap()

    def getconfirmbuttonobject(self):  # btn btn-lg btn-block btn-success
        objeto = Mapeamento("btn btn-lg btn-block btn-success")
        return objeto.getclassmap()

    def getresetpassbuttonobject(self):
        #objeto = Mapeamento(self.el)
        return ""
