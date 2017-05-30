import random
import argparse

#######################################################################################
#                                A E S T H E T I C                                    #
#######################################################################################
def coinToss():
	x = random.randint(0,99)
	return x <= 5

def ansRCO():
	ret = 'RCO\n'
	i 	= random.randint(0,4)
	if i == 0:
		s = 'Muy poco'
	elif i == 1:
		s = 'Poco'
	elif i == 2:
		s = 'Regular'
	elif i == 3:
		s = 'Bastante'
	elif i == 4:
		s = 'Mucho'
	ret+=str(i)+'\n5\n'+s+'\n\n'
	return ret	

def ansRCNOU():
	ret = 'RCNOU\n'
	i 	= random.randint(0,3)
	
	if i == 0:
		s = 'Rojo'
	elif i == 1:
		s = 'Azul'
	elif i == 2:
		s = 'Amarillo'
	elif i == 3:
		s = 'Verde'
	ret+=str(i)+'\n'+s + '\n\n'
	return ret
		
def ansRCNOM():
	ret = 'RCNOM\n'
	sel = set()
	n = random.randint(1,2)
	for i in range(0,n):
		x = random.randint(0,3)
		while x in sel:
			x = random.randint(0,3)
		sel.add(x)	
	ret+=str(n)+'\n'
	for x in sel:
		if x == 0:
			ret+='0\nOpel\n'
		if x == 1:
			ret+='1\nRenault\n'
		if x == 2:
			ret+='2\nHyundai\n'
		if x == 3:
			ret+='3\nNissan\n'	
	return ret+'\n'			

def ansRN():
	return 'RN\n'+str(random.uniform(0.0,10.0)) + '\n0.0\n10.0\n\n'

import random

s_nouns = ["A dude", "My mom", "The king", "Some guy", "A cat with rabies", "A sloth", "Your homie", "This cool guy my gardener met yesterday", "Superman"]
p_nouns = ["These dudes", "Both of my moms", "All the kings of the world", "Some guys", "All of a cattery's cats", "The multitude of sloths living under your bed", "Your homies", "Like, these, like, all these people", "Supermen"]
s_verbs = ["eats", "kicks", "gives", "treats", "meets with", "creates", "hacks", "configures", "spies on", "retards", "meows on", "flees from", "tries to automate", "explodes"]
p_verbs = ["eat", "kick", "give", "treat", "meet with", "create", "hack", "configure", "spy on", "retard", "meow on", "flee from", "try to automate", "explode"]
infinitives = ["to make a pie.", "for no apparent reason.", "because the sky is green.", "for a disease.", "to be able to make toast explode.", "to know more about archeology."]

def ansRL():
	return 'RL\n'+random.choice(s_nouns)+" "+ random.choice(s_verbs)+" "+ random.choice(s_nouns).lower() or random.choice(p_nouns).lower()+" "+ random.choice(infinitives) +'\n\n'




#######################################################################################
#                                A E S T H E T I C                                    #
#######################################################################################


parser = argparse.ArgumentParser()

parser.add_argument('--n', type=int, default=100,
                     help='El número de Encuestas a generar')



args = parser.parse_args()
if args.n == None :
    parser.print_help()
n = args.n   

for x in range (0,n):

	file = open('Encuesta_User'+str(x), 'w+')
	file.write('Título\nEncuesta\n\nUsuario\nUser'+str(x)+'\n\n')
	file.write('Fecha\n18/05/2017 10:11:12\n\n')

	file.write('Respuesta pregunta\n')
	if coinToss():
		file.write('RV\n')
	else:
		file.write(ansRCO())

	file.write('Respuesta pregunta\n')	
	if coinToss():
		file.write('RV\n')
	else:
		file.write(ansRCNOU())
	
	file.write('Respuesta pregunta\n')	
	if coinToss():
		file.write('RV\n')
	else:
		file.write(ansRCNOM())


	file.write('Respuesta pregunta\n')
	if coinToss():
		file.write('RV\n')
	else:
		file.write(ansRN())

	file.write('Respuesta pregunta\n')	
	if coinToss():
		file.write('RV\n')
	else:
		file.write(ansRL())				






