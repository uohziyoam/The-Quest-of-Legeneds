##
# Author: Phumin Walaipatchara
#
# Functionality: convert txt to csv in all and sub directory w.r.t current working directory

import os

PWD = os.path.abspath('./')+'/'

def recursiveTxt(fList, parent):
    for f in fList:
        if os.path.isdir(parent+f):
            recursiveTxt(os.listdir(parent+f+'/'), parent+f+'/')
        elif os.path.isfile(parent+f) and f[-4:] == '.txt':
            toCsv(parent+f)

def toCsv(filePath):
    buffer = ''
    with open(filePath, 'r') as f:
        buffer += f.readline().replace('/', ',')
        for line in f:
            if line != '\n':
                spaceOpen = False
                for c in line:
                    if c == ' ' or c == '\t':
                        if not spaceOpen:
                            spaceOpen = True
                    else:
                        if spaceOpen:
                            spaceOpen = False
                            if c == '\n':
                                buffer += c
                            else:
                                buffer += ','+c
                        else:
                            buffer += c

    with open(filePath.replace('.txt', '.csv'), 'w') as f:
        f.write(buffer)

recursiveTxt(os.listdir(), PWD)