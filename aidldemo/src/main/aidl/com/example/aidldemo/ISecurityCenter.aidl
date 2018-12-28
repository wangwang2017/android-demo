// ISecurityCente.aidl
package com.example.aidldemo;

// Declare any non-default types here with import statements

interface ISecurityCenter {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     * 提供加解密功能
     */
    String encrypt(String content);
    String decrypt(String password);
}
