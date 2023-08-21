import java.util.Scanner;
public class MainClass{
public static void main(String[] args) {
    Scanner _key = new Scanner(System.in);
String contadorTxt;
double contador;
contadorTxt = "Contador: ";

contador= _key.nextDouble();
System.out.println(contadorTxt);

System.out.println(contador);

contador = 5;

while (contador<=10){
System.out.println(contadorTxt);
System.out.println(contador);
contador = contador+1;

}
do{
System.out.println(contadorTxt);
System.out.println(contador);
contador = contador-1;

} while (contador>=0);

}}