package br.com.toldosjoia.banco_de_dados;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Base_de_Dados extends SQLiteOpenHelper {

	public Base_de_Dados(Context context) {
		super(context, "Toldos", null, 1);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
	
		db.execSQL("CREATE TABLE usuario ("+
                   "NOME varchar(50) NOT NULL,"+
                   "SENHA varchar(50) NOT NULL,"+
                   "COD_NOME int ,"+
                   "AUTOENTRAR CHAR(1) DEFAULT 'N')");
		
        db.execSQL("CREATE TABLE cliente ("+
				  "CODCLIENTE int(11) NOT NULL PRIMARY KEY ,"+
				  "NOME varchar(50) NOT NULL UNIQUE ,"+
				  "CONTATO varchar(50) NOT NULL,"+
				  "COMPLEMENTO varchar(50) DEFAULT NULL,"+
				  "RUA varchar(50) NOT NULL,"+
				  "BAIRRO varchar(50) DEFAULT NULL,"+
				  "NUMERO int(11) NOT NULL,"+
				  "TELEFONE char(10) DEFAULT NULL,"+
				  "Estado char(2) DEFAULT NULL,"+
				  "CEP char(10) DEFAULT NULL,"+
				  "REFERENCIA varchar(50) DEFAULT NULL,"+
				  "CELULAR char(11) DEFAULT NULL,"+
				  "cidade varchar(50) NOT NULL)");
        
        db.execSQL("CREATE TABLE material ("+
        		  "codmaterial int(11) NOT NULL PRIMARY KEY,"+
        		  "babado varchar(30) NOT NULL,"+
        		  "estrutura varchar(30) NOT NULL,"+
        		  "cor varchar(30) NOT NULL,"+
        		  "codpedido int(11) NOT NULL)");
        
        db.execSQL("CREATE TABLE modelo ("+
                   "codmodelo int(11) NOT NULL PRIMARY KEY,"+
                   "nome varchar(50) NOT NULL,"+
                   "largura float NOT NULL,"+
                   "tamanho float NOT NULL,"+
                   "h1 float(11) NOT NULL,"+
                   "codmaterial int(11) NOT NULL UNIQUE)");
        
        db.execSQL("CREATE TABLE pedidos ("+
                   "DATA_VENDA datetime NOT NULL,"+
                   "PRAZO_DE_ENTREGA int(11) NOT NULL,"+
                   "CODPEDIDO int(11) NOT NULL PRIMARY KEY,"+
                   "TIPO_DE_SERVICO varchar(20) NOT NULL,"+
                   "LIGOU_DIA date DEFAULT NULL,"+
                   "LIGOU_HORA time DEFAULT NULL,"+
                   "IR_DIA date DEFAULT NULL,"+
                   "IR_HORA time DEFAULT NULL,"+
                   "cliente varchar(50) NOT NULL)");
        
        db.execSQL("CREATE TABLE ULTIMA_ATUALIZACAO(ULTIMA TIMESTAMP,"+
        		                                    "ID int DEFAULT 0)");
        db.execSQL("CREATE TABLE SERVICE_ATIVO(aberto CHAR(1),"+
        	        		                         "cod int DEFAULT 0)");
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
