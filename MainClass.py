def main():
	string contadorTxt;
	float contador;
	contadorTxt = "Contador: "

	contador = float(input())

	print(contadorTxt)

	print(contador)

	contador = 5

	
	while contador<=10:
		print(contadorTxt)
		print(contador)
		contador = contador+1

		break

	
	while contador>=0:
		print(contadorTxt)
		print(contador)
		contador = contador-1

		break

