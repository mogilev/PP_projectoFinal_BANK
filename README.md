# PP_projectoFinal_BANK
Projecto Final de Paradigmas da Programação - 2019/2020



1 OBJETIVO

•Criar um Sistema de Transferências Bancárias usando Threads e Programação orientada a objetos. 

•Uso de  métodos  da  Classe  ReentrantLock  para  estabelecer  condições de bloqueio de threads. 

•Uso de métodos da Interface Condition (await e signalAll). 


2 DESCRIÇÃO DO TRABALHO 

Uma entidade financeira possui N contas bancárias(conta 1, conta 2....conta N). 

O saldo inicial máximo em cada conta é de € S. 

No decorrer do dia são realizadas transferências internas entre as contas, sendo que o saldo total final deve ser N x S. 

A transferência interna entre contas será realizada por Threads. 


Exemplo: 

Estado Inicial: 

Número de contas: 100 Saldo inicial máximo nas contas: € 2000 

Saldo inicial (total) = 100 * 2000 = € 200.000 


Transação: 

Transferir € 500,00 da conta n° 1 para conta n° 4 


Estado final: 

Conta n° 1= € 1.500 Conta n° 3 = € 2.500 Saldo final (total) = € 200.00


Exemplo de saída final de programa:

Thread  n° 1 
Quantidade Transferida 500 – de conta origem n° 1 – para conta destino n° 3 – Saldo final total 200.000

Thread  n° 2 
Quantidade Transferida 800.26–de conta origem n° 2 – para conta destino n° 4 – Saldo final total 200.000 

Thread n° 3
Quantidade Transferida 10.73 – de conta origem n° 3 – para conta destino n° N – Saldo final total 200.000


3 PROBLEMA 
Devido à falta de sincronização entre Threads, existe a possibilidade de que em alguns momentos  da  execução  do  programa  o saldo final total,  erroneamente, mostre saldo inferior a 200.000.


4 SOLUÇÃO 

-SINCRONIZAÇÃO DE THREADS 

Sincronização  de  threads,  através  de  métodos  da  classe  ReentrantLock e Interface Condition, para evitar assim, que várias threads entrem de forma simultânea para serem executadas.

A interface condition possui dois métodos:
await (): Coloca uma thread à espera e desbloqueia o código. 
signalAll(  ):  Informa  às  threads  (que  estão  em  fila  de  espera)  que  se  cumprida  uma condição passem para o estado Running.


5 REGRAS 

-Existem transferências infinitas entre todas as contas do banco. 

-A única operação bancária é transferência(não haverá levantamentos). 

-A  conta de destino, que recebe a transferência,será uma conta escolhida de forma aleatória. 

-Todas as transferências inicializadas devem ser realizadas. 

-Nenhuma transferência deverá gerar saldo negativo na conta de origem. 

-Não utilizar a classe Synchronized, Join, notifyAllouwait. 


6 MATERIAL DE CONSULTA 
https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/util/concurrent/locks/ReentrantLock.html 
https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/util/concurrent/locks/Condition.html
