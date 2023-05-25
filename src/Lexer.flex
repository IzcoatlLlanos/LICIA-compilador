import compilerTools.Token;

%%
%class Lexer
%type Token
%line
%column
%{
    private Token token(String lexeme, String lexicalComp, int line, int column){
        return new Token(lexeme, lexicalComp, line+1, column+1);
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
{Comentario}|{EspacioEnBlanco} { /*Ignorar*/ }

//Tipos de datos
cadena { return token(yytext(), "dato_digitos", yyline, yycolumn); }
torque | entero | potencia | rotacion | velocidad | presion | kilometraje | giro { return token(yytext(), "dato_entero", yyline, yycolumn); }
flotante | tiempo | temperatura | aceleracion { return token(yytext(), "dato_decimal", yyline, yycolumn); }
fecha { return token(yytext(), "dato_fecha", yyline, yycolumn); }
booleano { return token(yytext(), "dato_booleano", yyline, yycolumn); }

// los valores booleanos
verdadero | falso { return token(yytext(), "tipo_bool", yyline, yycolumn); }

//conversiones entero a flotante - flotante entero
converFlot | converEnt { return token(yytext(), "conversion", yyline, yycolumn); }

//raiz y exponente, trabajan con dos parametros
raiz | exp { return token(yytext(), "funcion_dos", yyline, yycolumn); }
//funciones trigonometricas, trabajan con un parametro
tan | sen | cos { return token(yytext(), "funcion_trig", yyline, yycolumn); }

//Componentes léxicos para la estructura principal del programa
inicio { return token(yytext(), "reservada_inicio", yyline, yycolumn); }
fin { return token(yytext(), "reservada_fin", yyline, yycolumn); }
principal { return token(yytext(), "reservada_principal", yyline, yycolumn); }

//función
funcion { return token(yytext(), "reservada_funcion", yyline, yycolumn); }
retorna { return token(yytext(), "reservada_retorna", yyline, yycolumn); }

//componente léxico para leer
leer { return token(yytext(), "reservada_leer", yyline, yycolumn); }

//componente léxico para sacar datos
imprime { return token(yytext(), "reservada_imprime", yyline, yycolumn); }

//componentes léxicos para la equivalencia de switch
caso { return token(yytext(), "reservada_caso", yyline, yycolumn); }
opcion { return token(yytext(), "reservada_opcion", yyline, yycolumn); }

//Reservadas para la equivalencia del "if"
si { return token(yytext(), "reservada_si", yyline, yycolumn); }
no { return token(yytext(), "reservada_no", yyline, yycolumn); }
si_no { return token(yytext(), "reservada_si_no", yyline, yycolumn); }

//componentes léxicos de un ciclo
ciclo { return token(yytext(), "reservada_ciclo", yyline, yycolumn); }
mientras { return token(yytext(), "reservada_mientras", yyline, yycolumn); }


//son las funciones que se pueden definir a una entidades o entidad_unica
obten | define | espera | apaga | enciende | detener { return token(yytext(), "funciones_entidad", yyline, yycolumn); }
rango { return token(yytext(), "rango_entidad", yyline, yycolumn); }

//entidades son aquellas en las cuales existe mas de una por ECU
//entidad_unica es aquella que solamente puede haber una
inyector | freno | aceite | bobina | cilindro | fallas | sensor { return token(yytext(), "entidades", yyline, yycolumn); }
motor | ventilador | motor_arranque | acelerador | coolant | montadora | modelo { return token(yytext(), "entidad_unica", yyline, yycolumn); }

//operadores
"&" | "|" | "||" { return token(yytext(), "operador_logico", yyline, yycolumn); }
"+" | "-" | "*" | "/" { return token(yytext(), "operador_aritmetico", yyline, yycolumn); }
"+=" | "-=" | "=" { return token(yytext(), "operador_asignacion", yyline, yycolumn); }
">=" | "<=" | "<" | ">" | "<>" | "==" { return token(yytext(), "operador_relacional", yyline, yycolumn); }

")" { return token(yytext(), "parentesis_c", yyline, yycolumn); }
"(" { return token(yytext(), "parentesis_a", yyline, yycolumn); }
"{" { return token(yytext(), "llaves_a", yyline, yycolumn); }
"}" { return token(yytext(), "llaves_c", yyline, yycolumn); }
"[" { return token(yytext(), "corchete_a", yyline, yycolumn); }
"]" { return token(yytext(), "corchete_c", yyline, yycolumn); }




"_"{Identificador} { return token(yytext(), "identificador", yyline, yycolumn); }
{Identificador} | 0{Digito} | {Digito}+"."(".")+(Digito)* { return token(yytext(), "error", yyline, yycolumn); }
{Digito}+ { return token(yytext(), "numero_entero", yyline, yycolumn); }
({Digito}+)"."({Digito}+) { return token(yytext(), "numero_decimal", yyline, yycolumn); }

"." { return token(yytext(), "punto", yyline, yycolumn); }
"," { return token(yytext(), "coma", yyline, yycolumn); }
";" { return token(yytext(), "puntoYcoma", yyline, yycolumn); }
":" { return token(yytext(), "doblePunto", yyline, yycolumn); }



. { return token(yytext(), "ERROR", yyline, yycolumn); }