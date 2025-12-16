#include "chars.h"

int isUpper(char c){
  return (c >='A' && c<= 'Z');
}

int isLower(char c){
  return (c >='a' && c<= 'z');
}

int isDigit(char c){
  return (c >='0' && c<= '9');
}