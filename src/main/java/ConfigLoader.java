import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

public class ConfigLoader {
    Map<String, Object> data;
    private static final Logger log = LoggerFactory.getLogger(ConfigLoader.class);
    public ConfigLoader(String path) {
        try {
            InputStream inputStream = new FileInputStream(new File(path));
            Yaml yaml = new Yaml();
            data = yaml.load(inputStream);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public Boolean isDevelopment() {
        return data.get("env").equals("development");
    }

    public Map<String, Object> getOntology() {
        return (Map<String, Object>)data.get("ontology");
    }

    public Map<String, Object> getIPFS() {
        return (Map<String, Object>)data.get("IPFS");
    }

    public Map<String, Object> getEthereum() {
        return (Map<String, Object>)data.get("ethereum");
    }


}
