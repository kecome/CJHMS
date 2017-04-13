package com.homework.util;

/**
 * 表示一个ID生成器
 *
 * @author Raven
 */
public interface IdGenerator {

    /**
     * 生成一个唯一的ID
     *
     * @return 生成的ID
     */
    long nextId();

}
