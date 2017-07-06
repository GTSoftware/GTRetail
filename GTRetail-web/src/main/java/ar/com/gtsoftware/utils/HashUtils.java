/*
 * Copyright 2014 GT Software.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ar.com.gtsoftware.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Rodrigo Tato
 */
public abstract class HashUtils {

    private static final Logger LOG = Logger.getLogger(HashUtils.class.getName());

    public static String getHash(String message) {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            sha.update(message.getBytes("UTF-8"));
            byte[] digest = sha.digest();
            String hash = Base64.encodeBase64String(digest);
            return hash;

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
