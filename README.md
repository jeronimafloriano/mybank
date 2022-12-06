# mybank
Aplicação SpringBoot standalone que simula a integração e processamento de contas para Receita Federal, enviadas via arquivo .csv integrando com um banco fictício ("My Bank") utilizando Java, Spring e MongoDB.

## Setup
### Requerimentos
* Java 14 ou superior
* Maven 3.6.3 ou superior
* OpenCsv 5.0 ou superior

## Funcionamento:

* Ao executar a aplicação as informações de contas (arquivo CSV) serão enviadas ao Banco Central.
* O "serviço da receita" irá realizar o processamento automático do arquivo, enviando a atualização para a Receita Federal através da classe ReceitaService e processando cada conta enviada.
* Após o processamento, as contas serão adicionadas no banco de dados  do "My Bank" (MONGODB) e a aplicação irá retornar um arquivo com o resultado do processamento das contas pela Receita Federal (Mesmo formato do arquivo enviado inicialmente, apenas adicionando uma nova coluna com o resultado do processamento).
