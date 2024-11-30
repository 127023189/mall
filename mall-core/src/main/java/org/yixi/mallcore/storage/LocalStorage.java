package org.yixi.mallcore.storage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

/**
 * 本地存储配置
 */
public class LocalStorage implements Storage {
    private Log logger = LogFactory.getLog(LocalStorage.class);

    private String storagePath;
    private String address;
    private Path rootLocation;
    public String getStoragePath() {
        return storagePath;
    }
    public void setStoragePath(String storagePath) {
        this.storagePath = storagePath;

        this.rootLocation = Paths.get(storagePath);
        try{
            Files.createDirectories(rootLocation);
        }catch(IOException e){
            logger.error(e.getMessage(),e);
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public void store(InputStream inputStream, long contentLength, String contentType, String keyName) {
        try {
            Files.copy(inputStream, rootLocation.resolve(keyName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file " + keyName, e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(rootLocation,1)
                    .filter(path -> !path.equals(rootLocation))
                    .map(path-> rootLocation.relativize(path));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read stored files", e);
        }
    }

    @Override
    public Path load(String keyName) {
        return rootLocation.resolve(keyName);
    }

    @Override
    public Resource loadAsResource(String keyName) {
        Resource resource = null;
        try {
            Path file = load(keyName);
            resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()){
                return resource;
            }
        } catch (MalformedURLException e) {
            logger.error(e.getMessage(),e);
        }
        return resource;
    }

    @Override
    public void delete(String keyName) {
        Path file = load(keyName);
        try {
            Files.delete(file);
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
        }
    }

    @Override
    public String generateUrl(String keyName) {
        return address + keyName;
    }
}
