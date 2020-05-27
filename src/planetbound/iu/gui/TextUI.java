/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetbound.iu.gui;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import planetbound.logica.PlanetBound;
import planetbound.logica.estados.EscolheNave;
import planetbound.logica.estados.EsperaMover;
import planetbound.logica.estados.EstacaoEspacial;
import planetbound.logica.estados.Eventos;
import planetbound.logica.estados.ExploraPlaneta;
import planetbound.logica.estados.GameOver;
import planetbound.logica.estados.IState;
import planetbound.logica.estados.OrbitaPlaneta;

/**
 *
 * @author Leonardo
 */
public class TextUI {
    private PlanetBound game;
    private boolean quit = false;
    

    public TextUI(PlanetBound game) 
    {
        this.game = game;
    }
    
    public void run() 
    {
        
        while (!quit) {            
            IState state = game.getEstado();
           
            if (state instanceof EscolheNave) {
                uiEscolheNave();
            } else if (state instanceof EsperaMover) {
                uiEsperaMover();
            } else if (state instanceof EstacaoEspacial) {
                uiEstacaoEspacial();
            } else if (state instanceof Eventos) {
                uiEventos();
            } else if (state instanceof ExploraPlaneta) {
                uiExploraPlaneta();
            } else if (state instanceof OrbitaPlaneta) {
                uiOrbitaPlaneta();
            } else if (state instanceof GameOver) {
                uiGameOver();
            }
        }
    }

    private void uiEscolheNave() {
        Scanner sc = new Scanner(System.in);
        BufferedReader bin = new BufferedReader(new InputStreamReader(System.in));
        String option;
        char c;
        
        System.out.println("----- BEM VINDO AO PLANET BOUND -----");
        System.out.println("----- ESCOLHA UM TIPO DE NAVE -----\n");
        
        while (true) {
            
            do{
                
                System.out.println();
                System.out.println("0 - Quit");
                System.out.println("1 - Nave do tipo MILITAR");
                System.out.println("2 - Nave do tipo EXPLORATORIA");
                System.out.println();
                
                System.out.println("3 - Continuar um jogo previamente guardado");
                System.out.println();                
                
                System.out.print("> ");
                
                option = sc.next();
                
                if(option.length() >= 1)
                    c = option.charAt(0);
                else
                    c = ' ';
                
            }while(c < '0' || c > '3');
            
            switch(c){
                
                case '0':
                    quit = true;
                    return;

                case '1':
                    
                    System.out.println("Nave do tipo MILITAR selecionada");
                    
                    game.escolheNaveMilitar();
                    System.out.println(game.getNave().toString());
                    
                    return;
                
                case '2': 
                    System.out.println("Nave do tipo EXPLORATORIA selecionada");
                    
                    game.escolheNaveExploratoria();
                    System.out.println(game.getNave().toString());
                    
                    return;
                   
                case '3':
                   
                    System.out.print("File name: ");
                    
                    try{
                        
                        String fileName = bin.readLine();
                        
                        if(fileName == null)
                            return;
                        
                        if(fileName.length() < 1)
                            return;
                        
                        game = retrieveGameFromFile(fileName);
                        
                    }catch(IOException | ClassNotFoundException e){
                        System.out.println("Operation failed: " + e);
                    }                    
                    
                    return;

                default:
                    return;
                    
            } //switch
            
        } //while
    
    } //uiEscolheNave

    private void uiEsperaMover() {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("\n----- MOVER PELO ESPACO -----\n");
        System.out.println("--> Prima qualquer tecla para mover");
        
        System.out.print("> ");
        
        sc.next();
        
        game.move();
        System.out.print(game.showLog());
    }
    
    private void uiEstacaoEspacial() {
        Scanner sc = new Scanner(System.in);
        String option;
        char c;
        
        System.out.println("----- BEM VINDO A ESTACAO ESPACIAL -----");
        
        while (true) {
            
            do{
                
                System.out.println();
                System.out.println("0 - Sair da Estacao Espacial");
                System.out.println("1 - Aumentar nivel da [ARMA]");
                System.out.println("2 - Aumentar nivel do [ARMAZEM]");
                System.out.println("3 - Contratar [TRIPULACAO]");
                System.out.println("4 - Mudar [RECURSO]");
                System.out.println("5 - Completar [ESCUDO]");
                System.out.println("6 - Trocar [RECURSO] por [ESCUDO]");
                System.out.println("7 - Trocar [RECURSO] por [MUNICAO]");
                System.out.println("8 - Trocar [RECURSO] por [COMBUSTIVEL]");
                System.out.println("9 - Comprar [DRONE]");
                System.out.println();
               
                System.out.print("> ");
                
                option = sc.next();
                
                if(option.length() >= 1)
                    c = option.charAt(0);
                else
                    c = ' ';
                
            }while(c < '0' || c > '9');
            
            switch(c){
                
                case '0':
                    System.out.println("\nSaindo da Estacao Espacial");
                    game.saiEstacao();
                    return;

                case '1':
                    
                    System.out.println("\nVerificando condicoes para aumentar nivel da [ARMA]");
                    game.nivelArmaUp();
                    System.out.println(game.getNaveString());
                    return;
                
                case '2': 
                    
                    System.out.println("\nVerificando condicoes para aumentar nivel do [ARMAZEM]");
                    game.nivelArmazemUp();
                    System.out.println(game.getNaveString());
                    return;
                   
                case '3':
                   
                    System.out.println("\nVerificando condicoes para contratar [TRIPULANTE]");
                    game.contrataTripulacao();
                    System.out.println(game.getNaveString());
                    return;
                    
                case '4':
                   
                    System.out.println("\nTroca de recursos");
                    trocaRecurso();
                    System.out.println(game.getNaveString());
                    return;

                case '5':

                    System.out.println("\nVerificando condicoes para completar [ESCUDO]");
                    game.completaEscudo();
                    System.out.println(game.getNaveString());
                    return;

                case '6':
                    
                    System.out.println("\nVerificando condicoes para trocar [RECURSO] por [ESCUDO]");
                    game.trocaRecursoEscudo();
                    System.out.println(game.getNaveString());
                    return;

                case '7':

                    System.out.println("\nVerificando condicoes para trocar [RECURSO] por [MUNICAO]]");
                    game.trocaRecursoMunicao();
                    System.out.println(game.getNaveString());
                    return;

                case '8':

                    System.out.println("\nVerificando condicoes para trocar [RECURSO] por [COMBUSTIVEL]");
                    game.trocaRecursoCombustivel();
                    System.out.println(game.getNaveString());
                    return;

                case '9':

                    System.out.println("\nVerificando condicoes para comprar [DRONE]");
                    game.compraDrone();
                    System.out.println(game.getNaveString());
                    return;
                    
                default:
                    return;
                    
            } //switch
        }
    }

    private void trocaRecurso(){
        Scanner sc = new Scanner(System.in);
        Map<Character, String> opcaoRecursos = new HashMap<>();
        String userInput;
        String[] options;
        char option1 = ' ';
        char option2 = ' ';
        
        opcaoRecursos.put('1', "Negro");
        opcaoRecursos.put('2', "Vermelho");
        opcaoRecursos.put('3', "Verde");
        opcaoRecursos.put('4', "Azul");
        
        System.out.println("\nBenvindo a troca de recusos, escolha o recurso que queiras trocar"
        + " e o recurso que desejas adquirir");
        System.out.println("\nExemplo: 1,4 troca [NEGRO] por [AZUL]");
        
        do{

            System.out.println();
            System.out.println("0 - Sair");
            System.out.println("1 - Negro");
            System.out.println("2 - Vermelho");
            System.out.println("3 - Verde");
            System.out.println("4 - Azul");
            
            System.out.println();

            System.out.println();                

            System.out.print("> ");

            userInput = sc.next();
            if(userInput.trim().equals("0")){
                return;
            }
            if(userInput.length() >= 3){
                options = userInput.replaceAll(" ","").split(",");
                option1 = options[0].charAt(0);
                option2 = options[1].charAt(0);
            }
            

        }while( option1 < '1' || option1 > '5' || option2 < '1' || option2 > '5' );

        game.mudaRecurso(opcaoRecursos.get(option1), opcaoRecursos.get(option2));
        System.out.print(game.getNaveString());
    }
    
    private void uiEventos() {
        Scanner sc = new Scanner(System.in);
        int opt;
        System.out.println("\n----- UM EVENTO ACONTECEU ENQAUNTO SE LOCOMOVIA -----\n");
        System.out.println("\n----- Evento aleatorio (1) ou evento a escolha (2)? -----\n");
        System.out.print("> ");
        
        opt = sc.nextInt();
        if(opt == 1){
            game.aplicaEvento();
        }else if(opt == 2){
            uiAplicaEvento();
        }
        
        System.out.print(game.showLog());
        System.out.println();
        System.out.println(game.getNaveString());
    }
    
    private void uiAplicaEvento(){
        Scanner sc = new Scanner(System.in);
        int option;
        
        System.out.println("\n----- APLICA EVENTO -----");
           
        do{

            System.out.println();
            System.out.println("0 - Sair");
            System.out.println("1 - Morte tripulante");
            System.out.println("2 - Saquear destrocos de nave");
            System.out.println("3 - Perde recurso");
            System.out.println("4 - Perde combustivel");
            System.out.println("5 - Sem evento");
            System.out.println("6 - Recupera tripulante");
            System.out.println();

            System.out.print("> ");

            option = sc.nextInt();
            System.out.print(game.showLog());
        }while(option < 0 || option > 6);
        game.aplicaEvento(option);
    }

    private void uiExploraPlaneta() {
        Scanner sc = new Scanner(System.in);
        int option;
        
        System.out.println("----- EXPLORA PLANETA -----");
        
       
         do{
                
            System.out.println(); 
            System.out.println("1 - Cima");
            System.out.println("2 - Baixo");
            System.out.println("3 - Esquerda");
            System.out.println("4 - Direita");

            System.out.println();
            System.out.print(game.showGrid());
            System.out.println();
            System.out.print("\n> ");

            option = sc.nextInt();
        }while(option < 1 || option > 4);

        game.movimentacaNoMapa(option);
        System.out.println(game.showLog());
        
    }

    private void uiOrbitaPlaneta() {
        Scanner sc = new Scanner(System.in);
        String option;
        char c;
        boolean estacao = game.temEstacao();
        
        
        System.out.println("\n----- Chegaste a um planeta -----");
        
        while (true) {
            
            do{
                
                System.out.println();
                System.out.println("0 - Quit");
                System.out.println("1 - Esplorar Planera");
                System.out.println("2 - Proximo Turno");
                if(estacao)
                    System.out.println("3 - Entrar na Estacao Espacial");
                System.out.println();
                System.out.println("4 - Mostra log");
                System.out.println();
                System.out.println("5 - Guardar jogo");
                System.out.println();
                
                System.out.print("> ");
                
                option = sc.next();
                
                if(option.length() >= 1){
                    c = option.charAt(0);
                    if(c == '4')
                        System.out.print(game.showLogAll());
                    if(c == '5'){
                        try{                
                            handleSaveGameToFileOption();
                            game = new PlanetBound(); //novo jogo               
                        }catch(IOException e){            
                            System.out.println("Operation failed: " + e);
                        } 
                    }
                }
                else
                    c = ' ';
                if(!estacao && c == '3')
                    c = ' ';
            }while(c < '0' || c > '3');
            
            switch(c){
                
                case '0':
                    quit = true;
                    return;

                case '1':
                    
                    System.out.println("\nIniciando a exploracao do planeta");
                    
                    game.iniciaExploracao();
                   
                    return;
                
                case '2': 
                    System.out.println("\nProximo turno");
                    
                    game.nextTurn();;
                    
                    return;
                   
                case '3':
                   System.out.println("\nEntrando na estacao espacial");
                   
                   game.entraEstacao();
                    
                    return;
                    
                 case '4':
                   System.out.println("\nGuardando jogo");
                   
                   game.entraEstacao();
                    
                    return;
                default:
                    return;
                    
            } //switch
        }//while
    }

    private void uiGameOver() {
        Scanner sc = new Scanner(System.in);
        int option;
        System.out.println("\nGAME OVER\n");
        do{
            System.out.println("0 - Sair do jogo");
            System.out.println("1 - Começar um novo jogo");
            option = sc.nextInt();
        }while(option < 0 || option > 1);
        
        if(option == 0){
            System.out.println("----- Obrigado por ter jogado -----");
            game.terminaJogo();
        }else{
            game.recomecaJogo();
        }
    }
    /**
     * Funcao retirada do exercicio pratio ThreeInRowGame e adaptada para este programa
     * handler para guardar o jogo em um fichei fileName.bin
     * @throws IOException Caso haja alguma falha na abertura do ficheiro
     */
    private void handleSaveGameToFileOption() throws IOException
    {
        String fileName;
        
        System.out.print("File name: ");
        fileName = new BufferedReader(new InputStreamReader(System.in)).readLine();
        
        if(fileName == null)
            return;

        if(fileName.length() < 1)
            return;
                
        saveGameToFile(fileName);     
        
    }
    /**
     * Funcao retirada do exercicio pratio ThreeInRowGame e adaptada para este programa
     * guarda o jogo em um fichei fileName.bin
     * @param fileName nome do ficheiro que deseja guardar o jogo
     * @throws IOException Caso haja alguma falha na abertura do ficheiro
     */
    private void saveGameToFile(String fileName) throws IOException
    {
        ObjectOutputStream oout = null;
        try{
            oout = new ObjectOutputStream(new FileOutputStream(fileName));
            oout.writeObject(game); //writeUnshared
            oout.close();
        }finally{
            if(oout != null)
                oout.close();
        }
    }
    /**
     * Funcao retirada do exercicio pratio ThreeInRowGame e adaptada para este programa
     * recupera o jogo em um fichei fileName.bin
     * @param fileName nome do ficheiro que deseja recuperar o jogo
     * @return Um objeto do tipo PlanetBound com os dados recuperados
     * @throws IOException Caso haja alguma falha na abertura do ficheiro
     * @throws ClassNotFoundException caso o nome do ficheiro estaja errado
     */
    private PlanetBound retrieveGameFromFile(String fileName) throws IOException, ClassNotFoundException
    {
        ObjectInputStream oin = null;
        try{
            oin = new ObjectInputStream(new FileInputStream(fileName));
            return (PlanetBound)oin.readObject();
        }finally{
            if(oin != null) oin.close();
        }
        
    }   
    
}
