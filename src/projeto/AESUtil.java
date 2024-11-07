package projeto;
import javax.crypto.Cipher; // Classe que fornece as funcionalidades de criptografia e descriptografia.
import javax.crypto.KeyGenerator; // Classe para gerar chaves criptográficas (não usada diretamente neste exemplo).
import javax.crypto.SecretKey; // Interface que representa uma chave secreta para algoritmos de chave simétrica.
import javax.crypto.spec.SecretKeySpec; // Classe que permite criar uma chave a partir de um array de bytes.
import java.util.Base64; // Classe para codificação e decodificação em Base64, que facilita a transmissão de dados binários.
import java.io.File; // Importa a classe File para lidar com arquivos
import java.io.FileInputStream;
import java.io.FileNotFoundException; // Exceção lançada quando um arquivo não é encontrado
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.NoSuchElementException; // Exceção lançada quando não há mais elementos a serem lidos
import java.util.Scanner; // Importa Scanner para ler a entrada do arquivo e do console

public class AESUtil {
        // Define o algoritmo de criptografia usado: AES (Advanced Encryption Standard).
        private static final String ALGORITHM = "AES";
        // Um array de bytes que representa a chave secreta usada para criptografar e descriptografar (16 bytes = 128 bits).
        //private static final byte[] keyValue = "1234567890123456".getBytes();
        private static Scanner input; // Scanner para ler o arquivo de texto
        private static String chave; 
            
                // Método para criptografar uma string de texto.
                public static String encrypt(String data) throws Exception {

                    // Gera uma chave aleatória, cria um arquivo chave.txt e armazena a chave nele <- IGNORAR
                    //geraChave(new File("src/projeto/chave.txt")); <- IGNORAR

                    // Gera uma chave `SecretKey` usando a função `generateKey`.
                    SecretKey key = generateKey();
                    // Cria uma instância do `Cipher` configurada para usar o algoritmo AES.
                    Cipher cipher = Cipher.getInstance(ALGORITHM);
                    // Inicializa o `Cipher` em modo de criptografia (ENCRYPT_MODE) usando a chave fornecida.
                    cipher.init(Cipher.ENCRYPT_MODE, key);
                    // Criptografa os dados, transformando a string em bytes antes de processar.
                    byte[] encryptedData = cipher.doFinal(data.getBytes());
                    // Converte os bytes criptografados em uma string Base64 para facilitar a transmissão.
                    return Base64.getEncoder().encodeToString(encryptedData);
                }
            
                // Método para descriptografar uma string que foi criptografada.
                public static String decrypt(String encryptedData) throws Exception {
                    // Gera novamente a `SecretKey` usando a função `generateKey`.
                    SecretKey key = generateKey();
                    // Cria uma instância do `Cipher` configurada para usar o algoritmo AES.
                    Cipher cipher = Cipher.getInstance(ALGORITHM);
                    // Inicializa o `Cipher` em modo de descriptografia (DECRYPT_MODE) usando a mesma chave.
                    cipher.init(Cipher.DECRYPT_MODE, key);
                    // Decodifica a string Base64 de volta para bytes.
                    byte[] decodedData = Base64.getDecoder().decode(encryptedData);
                    // Descriptografa os bytes decodificados, retornando os bytes da mensagem original.
                    byte[] decryptedData = cipher.doFinal(decodedData);
                    // Converte os bytes descriptografados de volta para uma string e retorna o resultado.
                    return new String(decryptedData);
                }
            
                // Método privado que le a chave gravada no chave.txt
                private static SecretKey generateKey() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, CertificateException, KeyStoreException, IOException, ClassNotFoundException {
                   // =========================== IGNORAR ===============================================================
                    // ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/projeto/chave.txt"));
                    // SecretKey iSim = (SecretKey) ois.readObject();
                    // byte[] chave = iSim.getEncoded();
                    // ois.close();
                    // ==================================================================================================

                    // Acessa o arquivo chave.txt para ler a chave que está gravada nele e a retorna
                    openFile();
                    lerChave();
                    closeFile();
                    byte[] keyValue = chave.getBytes();
                    return new SecretKeySpec(keyValue, ALGORITHM);
                }
            
            
                // Método para abrir o arquivo e iniciar o Scanner
                public static void openFile() {
                    try {
                        // Abre o arquivo "chave.txt" para leitura
                        input = new Scanner(new File("src/projeto/chave.txt"));
                } 
                catch (FileNotFoundException fileNotFoundException) {
                    // Se o arquivo "chave.txt" não for encontrado, exibe uma mensagem de erro
                    System.err.println("Error opening file.");
                    System.exit(1); // Encerra o programa com erro
                }  
            } 
        
            // Método para ler os registros do arquivo e verificar as credenciais
            public static void lerChave() {
                try {
                    // Continua lendo registros do arquivo enquanto houver dados
                    while (input.hasNext()) {
                        chave = input.next();
            }
        }
        // Trata exceção caso o arquivo tenha um formato incorreto
        catch (NoSuchElementException elementException) {
            System.err.println("File improperly formed.");
            input.close();
            System.exit(1);
        }
        // Trata exceção caso haja um erro na leitura do arquivo
        catch (IllegalStateException stateException) {
            System.err.println("Error reading from file.");
            System.exit(1);
        }
    } 

    // Método que gera uma chave AES de 128 bits e a salva em um arquivo
   public static void geraChave(File fSim)
      throws IOException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, 
             CertificateException, KeyStoreException {
      // Gera uma chave AES simétrica de 128 bits
      KeyGenerator kg = KeyGenerator.getInstance("AES");  
      kg.init(128); // Inicializa o gerador para gerar uma chave de 128 bits
      SecretKey sk = kg.generateKey(); // Gera a chave AES

      // Grava a chave gerada em um arquivo usando serialização
      ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fSim));  
      oos.writeObject(sk);  // Escreve o objeto (chave) no arquivo
      oos.close();  // Fecha o stream de escrita
   }

    // Método para fechar o arquivo e o Scanner de entrada
    public static void closeFile() {
        if (input != null) {
            input.close(); // Fecha o Scanner que lê o arquivo
        }                
    }

    public void setChave(String c) {
       chave = c;
    }
    public String getChave() {
        return chave;
    }
}

