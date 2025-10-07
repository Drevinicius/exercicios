#include <stdio.h>
#include <stdlib.h>

//#define TAM 5

typedef struct no{
    int valor;
    struct no *prox;
}nodo;

nodo *criar(int valor){
    nodo *novo = (nodo *)malloc(sizeof(nodo));
    if(novo == NULL){
        return NULL;
    }
    novo->valor = valor;
    novo->prox = NULL;
    
    return novo;
}

void inserirInicio(nodo **cabeca, int valor){
    nodo *novo = criar(valor);
    if(!(*cabeca)){
        *cabeca = novo;
    }else{
        novo->prox = *cabeca;
        *cabeca = novo;
    }
}
void inserirFim(nodo **cabeca, int valor){
    nodo *novo = criar(valor);
    if(!(*cabeca)){
        *cabeca = novo;
    }else{
        nodo *atual = *cabeca;
        
        while(atual->prox != NULL)
            atual = atual->prox;
        atual->prox = novo;
    }
}
void inserirPos(nodo **cabeca, int valor, int pos){
    nodo *novo = criar(valor);
    if(!(*cabeca)){
        *cabeca = novo;
    }else if(pos == 1){
        inserirInicio(cabeca, valor);
    }else{
        int auxPos = 2;
        nodo *anterior = *cabeca; //primeira casa;
        nodo *atual = (*cabeca)->prox; // Segunda casa;
    }
}
void imprimir(nodo *cabeca){
    if(!cabeca){
        printf("\nLista vazia\n");
    }else{
        nodo *atual = cabeca;
        while(atual){
            printf("[%d]", atual->valor);
            atual = atual->prox;
        }
        printf("\n");
    }
}

int main() {
    nodo *lista = NULL;
    
    inserirInicio(&lista, 4);
    inserirFim(&lista, 5);
    inserirFim(&lista, 6);
    inserirInicio(&lista, 3);
    inserirInicio(&lista, 2);
    inserirInicio(&lista, 1);

    imprimir(lista);
    /*for(int i = 0; i < TAM; i++){
        printf("%d - Digite um valor para o vetor: ", i);
        scanf("%d", vetor);
        vetor++;
    }
    
    
    //imprimirVet(vetor); Acontece um erro de referencia
    vetor = aux;
    imprimirVet(vetor); // Imprime reverso
    */
    
    free(lista);
    return 0;
}
