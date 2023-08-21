def main():
	
	
	contadorTxt = "Contador: "

	contador = float(input())

	print(contadorTxt)

	print(contador)

	contador = 5

	
	while contador<=10:
		print(contadorTxt)
		print(contador)
		contador = contador+1

	
	while contador>=0:
		print(contadorTxt)
		print(contador)
		contador = contador-1


if __name__ == '__main__':
	main()