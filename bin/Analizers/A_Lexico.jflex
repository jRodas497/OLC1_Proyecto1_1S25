/*------------------------------------------------------------------- 
------------------------ 1 PACKAGE E IMPORTS-------------------------
-------------------------------------------------------------------*/

package Analizers;
import java_cup.runtime.symbol;
import java.util.ArrayList;

%%
/*------------------------------------------------------------------- 
------------------ 2 CONFIGS PARA EL ANLÍSIS LÉXICO------------------
-------------------------------------------------------------------*/
%{
// === CODIGO JAVA
    string cadena = ""
    public static LinkedList<AceptErr> TablaErrores = new LinkedList<AceptErr>();
%}

// === DIRECTIVAS
%public
%class ALexico
%cupsym Simbolos
%cup
%char
%column
%full
%line

// === CONSTRUCTOR
%init{
    yyline = 1;
    yychar = 1;
%init}

// === EXPRESIONES REGULARES
UNUSED = [ \t\r]+
DIGITOS = [0-9]+(\.[0-9]+)?
LETRA = [a-zA-ZñÑ_]+
ESPACIO = [ \t\r]+

%{
    public String lexeme;
%}
%%

/*------------------------------------------------------------------- 
------------------------ 3 REGLAS SEMANTICAS-------------------------
-------------------------------------------------------------------*/
{UNUSED}    {}
"//".*  {/*ignore*/}    
{ESPACIO}   {/*ignore*/}
"\n"       {yyline++; return new Symbol(Terminal.Linea, yyline, yychar, yytext()); }
("(-"{DIGITOS}")")|{DIGITOS} { lexeme = yytext(); return new Symbol(Terminal.Numero); }
 .                     {return ERROR;}