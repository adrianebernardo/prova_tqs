package Framework.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileOperations {

    private static final String ARQUIVO_PROPERTIES = "Properties/user.properties";

    private static Properties properties;

   
    private static Properties carregar() {
        if (properties == null) {
            properties = new Properties();
            try (InputStream entrada = FileOperations.class.getClassLoader()
                    .getResourceAsStream(ARQUIVO_PROPERTIES)) {
                if (entrada == null) {
                    throw new IllegalStateException("Arquivo não encontrado: " + ARQUIVO_PROPERTIES);
                }
                properties.load(entrada);
            } catch (IOException e) {
                throw new IllegalStateException("Erro ao ler " + ARQUIVO_PROPERTIES, e);
            }
        }
        return properties;
    }

    public static String getProperty(String chave) {
        String valor = carregar().getProperty(chave);
        if (valor == null) {
            throw new IllegalArgumentException("Chave não encontrada no properties: " + chave);
        }
        return valor.trim();
    }

    public static double getPropertyDouble(String chave) {
        return Double.parseDouble(getProperty(chave));
    }
}
