package com.pentair.showcase.common.dao;

import java.util.*;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.pentair.showcase.common.entity.FileUpload;

@Component
public class FileUploadDao extends HibernateDao<FileUpload, String> {

    public FileUpload getFileUploadByPackAndName(String saveName, String srcName, String directory, String subdirectory) {
        if (subdirectory != null && !"".equals(subdirectory)) {
            return this.findUnique("FROM FileUpload WHERE fileSrcName =? AND fileSaveName = ? AND fileDirectory = ? AND fileSubDirectory = ?", srcName, saveName, directory, subdirectory);
        } else {
            return this.findUnique("FROM FileUpload WHERE fileSrcName =? AND fileSaveName = ? AND fileDirectory = ?", srcName, saveName, directory);
        }
    }

    public List<FileUpload> findFileUploadByPackAndDirectoty(String srcName, String directory, String subdirectory) {
        directory = directory.replace('&', '_');
        int index = directory.indexOf('_');
        String directory2 = null;
        if (index != -1) {
            directory2 = directory.substring(0, index);
        }
        if (subdirectory != null && !"".equals(subdirectory)) {
            if (directory2 == null) {
                return this.find("FROM FileUpload WHERE fileSrcName =? AND fileDirectory = ? AND fileSubDirectory = ?", srcName, directory, subdirectory);
            } else {
                return this.find("FROM FileUpload WHERE fileSrcName =? AND (fileDirectory = ? or fileDirectory = ?) AND fileSubDirectory = ?", srcName, directory, directory2, subdirectory);
            }
        } else {
            if (directory2 == null) {
                return this.find("FROM FileUpload WHERE fileSrcName =? AND fileDirectory = ?", srcName, directory);
            } else {
                return this.find("FROM FileUpload WHERE fileSrcName =? AND (fileDirectory = ? or fileDirectory = ?)", srcName, directory, directory2);
            }
        }
    }

    @Transactional
    public void deleteAll(Collection<FileUpload> entities) {
        if (entities != null) {
            for (FileUpload entity : entities) {
                this.delete(entity);
            }
        }

    }
}