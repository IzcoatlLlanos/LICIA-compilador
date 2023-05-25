import compilerTools.TextColor;
import java.awt.Color;

%%
%class LexerColor
%type TextColor
%char
%{
    private TextColor textColor(long start, int size, Color color){
        return new TextColor((int) start, size, color);
    }
%}
/* Variables básicas de comentarios y espacios */
TerminadorDeLinea = \r|\n|\r\n
EntradaDeCaracter = [^\r\n]
EspacioEnBlanco = {TerminadorDeLinea} | [ \t\f]
ComentarioTradicional = "/*" [^*] ~"*/" | "/*" "*"+ "/"
FinDeLineaComentario = "//" {EntradaDeCaracter}* {TerminadorDeLinea}?
ContenidoComentario = ( [^*] | \*+ [^/*] )*
ComentarioDeDocumentacion = "/**" {ContenidoComentario} "*"+ "/"

/* Comentario */
Comentario = {ComentarioTradicional} | {FinDeLineaComentario} | {ComentarioDeDocumentacion}

/* Identificador */
Letra = [A-Za-zÑñ_ÁÉÍÓÚáéíóúÜü]
Digito = [0-9]
Identificador = {Letra}({Letra}|{Digito})*

/* Número */
Numero = 0 | [1-9][0-9]*
%%

/* Comentarios o espacios en blanco */
{Comentario} { return textColor(yychar, yylength(), new Color(146, 146, 146)); }
{EspacioEnBlanco} { /*Ignorar*/ }

//Tipos de datos
cadena { /*Ignorar*/ }
torque | entero | potencia | rotacion | velocidad | presion | kilometraje | giro { return textColor(yychar, yylength(), new Color(6, 90, 158)); }
flotante | tiempo | temperatura | aceleracion { /*Ignorar*/ }
fecha { /*Ignorar*/ }
booleano { /*Ignorar*/ }

// los valores booleanos
verdadero | falso { /*Ignorar*/ }

//conversiones entero a flotante - flotante entero
converFlot | converEnt { /*Ignorar*/ }

//raiz y exponente, trabajan con dos parametros
raiz | exp { /*Ignorar*/ }
//funciones trigonometricas, trabajan con un parametro
tan | sen | cos { /*Ignorar*/ }

//Componentes léxicos para la estructura principal del programa
inicio { /*Ignorar*/ }
fin { /*Ignorar*/ }
principal { /*Ignorar*/ }

//función
funcion { /*Ignorar*/ }
retorna { /*Ignorar*/ }

//componente léxico para leer
leer { return textColor(yychar, yylength(), new Color(9, 110, 51)); }

//componente léxico para sacar datos
imprime { return textColor(yychar, yylength(), new Color(9, 110, 51)); }

//componentes léxicos para la equivalencia de switch
caso { /*Ignorar*/ }
opcion { return textColor(yychar, yylength(), new Color(6, 90, 158)); }

//Reservadas para la equivalencia del "if"
si { return textColor(yychar, yylength(), new Color(255, 0, 0)); }
no { return textColor(yychar, yylength(), new Color(255, 0, 0)); }
si_no { return textColor(yychar, yylength(), new Color(255, 0, 0)); }

//componentes léxicos de un ciclo
ciclo { return textColor(yychar, yylength(), new Color(0, 0, 255)); }
mientras { return textColor(yychar, yylength(), new Color(0, 0, 255)); }


//son las funciones que se pueden definir a una entidades o entidad_unica
obten | define | espera | apaga | enciende | detener { /*Ignorar*/ }
rango { /*Ignorar*/ }

//entidades son aquellas en las cuales existe mas de una por ECU
//entidad_unica es aquella que solamente puede haber una
inyector | freno | aceite | bobina | cilindro | fallas | sensor { return textColor(yychar, yylength(), new Color(0, 255, 0)); }
motor | ventilador | motor_arranque | acelerador | coolant | montadora | modelo { return textColor(yychar, yylength(), new Color(0, 255, 0)); }

//operadores
"&" | "|" | "||" { /*Ignorar*/ }
"+" | "-" | "*" | "/" { /*Ignorar*/ }
"+=" | "-=" | "=" { /*Ignorar*/ }
">=" | "<=" | "<" | ">" | "<>" | "==" { /*Ignorar*/ }

")" { /*Ignorar*/ }
"(" { /*Ignorar*/ }
"{" { /*Ignorar*/ }
"}" { /*Ignorar*/ }
"[" { /*Ignorar*/ }
"]" { /*Ignorar*/ }




"_"{Identificador} { /*Ignorar*/ }
{Identificador} | 0{Digito} | {Digito}+"."(".")+(Digito)* { /*Ignorar*/ }
{Digito}+ { /*Ignorar*/ }
({Digito}+)"."({Digito}+) { /*Ignorar*/ }

"." { /*Ignorar*/ }
";" { /*Ignorar*/ }
":" { /*Ignorar*/ }



. { /*Ignorar*/ }
