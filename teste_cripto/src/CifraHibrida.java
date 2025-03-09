import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;

public class CifraHibrida {
    private PublicKey chavePublica;
    private PrivateKey chavePrivada;

    /**
     * Construtor: Gera um par de chaves RSA de 2048 bits.
     */
    public CifraHibrida() throws Exception {
        KeyPairGenerator geradorParChaves = KeyPairGenerator.getInstance("RSA");
        geradorParChaves.initialize(2048);
        KeyPair parChaves = geradorParChaves.generateKeyPair();
        this.chavePublica = parChaves.getPublic();
        this.chavePrivada = parChaves.getPrivate();
    }

    /**
     * Classe interna para armazenar os dados cifrados.
     */
    public static class DadosCifrados {
        private byte[] chaveSessaoCifrada;
        private byte[] iv;
        private byte[] textoCifrado;

        public DadosCifrados(byte[] chaveSessaoCifrada, byte[] iv, byte[] textoCifrado) {
            this.chaveSessaoCifrada = chaveSessaoCifrada;
            this.iv = iv;
            this.textoCifrado = textoCifrado;
        }

        public byte[] getChaveSessaoCifrada() {
            return chaveSessaoCifrada;
        }

        public byte[] getIv() {
            return iv;
        }

        public byte[] getTextoCifrado() {
            return textoCifrado;
        }
    }

    /**
     * Método que realiza a cifragem do texto.
     */
    public DadosCifrados cifrar(String textoClaro) throws Exception {
        // Geração da chave simétrica AES (128 bits)
        KeyGenerator geradorChave = KeyGenerator.getInstance("AES");
        geradorChave.init(128);
        SecretKey chaveAES = geradorChave.generateKey();

        // Cifragem do texto com AES/GCM
        Cipher cifraAES = Cipher.getInstance("AES/GCM/NoPadding");
        byte[] iv = new byte[12]; // IV de 12 bytes (recomendado para GCM)
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        GCMParameterSpec parametroGCM = new GCMParameterSpec(128, iv); // Tag de 128 bits
        cifraAES.init(Cipher.ENCRYPT_MODE, chaveAES, parametroGCM);
        byte[] textoCifrado = cifraAES.doFinal(textoClaro.getBytes("UTF-8"));

        // Cifragem da chave de sessão com RSA/OAEP (usando SHA-256)
        Cipher cifraRSA = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        cifraRSA.init(Cipher.ENCRYPT_MODE, chavePublica);
        byte[] chaveSessaoCifrada = cifraRSA.doFinal(chaveAES.getEncoded());

        return new DadosCifrados(chaveSessaoCifrada, iv, textoCifrado);
    }

    /**
     * Método que realiza a decifragem dos dados.
     */
    public String decifrar(DadosCifrados dados) throws Exception {
        // Decifragem da chave de sessão com RSA/OAEP
        Cipher cifraRSA = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        cifraRSA.init(Cipher.DECRYPT_MODE, chavePrivada);
        byte[] chaveAESBytes = cifraRSA.doFinal(dados.getChaveSessaoCifrada());
        SecretKey chaveAES = new SecretKeySpec(chaveAESBytes, "AES");

        // Decifragem do texto com AES/GCM
        Cipher cifraAES = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec parametroGCM = new GCMParameterSpec(128, dados.getIv());
        cifraAES.init(Cipher.DECRYPT_MODE, chaveAES, parametroGCM);
        byte[] textoClaroBytes = cifraAES.doFinal(dados.getTextoCifrado());

        return new String(textoClaroBytes, "UTF-8");
    }

    /**
     * Método auxiliar para converter um array de bytes em uma String hexadecimal.
     */
    public static String bytesParaHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }

    /**
     * Método auxiliar para converter uma String hexadecimal em um array de bytes.
     */
    public static byte[] hexParaBytes(String hex) {
        int tamanho = hex.length();
        byte[] dados = new byte[tamanho / 2];
        for (int i = 0; i < tamanho; i += 2) {
            dados[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }
        return dados;
    }
}
