/* 
 * Copyright (C) 2013-2015 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License").
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * Create by ZollTy on 2013-6-27 (http://blog.zollty.com/, zollty@163.com)
 */
package org.zollty.util;

import java.io.PrintStream;
import java.io.PrintWriter;

import org.zollty.util.support.ExceptionDelegateSupport;
import org.zollty.util.support.NestedExceptionDelegate;

/**
 * NestedRuntimeException
 * 用于包装异常信息
 * @author zollty
 * @since 2013-6-27
 */
public class NestedRuntimeException extends BasicRuntimeException implements NestedException, ExceptionDelegateSupport {
    
    private static final long serialVersionUID = -4150549737760982517L;

    private final Throwable exception;
    
    private final NestedExceptionDelegate delegate;
    
    /**
     * @param message 自定义错误信息
     * @param args 占位符参数--[ 变长参数，用于替换message字符串里面的占位符"{}" ]
     */
    public NestedRuntimeException(String message, String... args) {
        this.exception = null;
        this.delegate = new NestedExceptionDelegate(null, message, args);
    }
    
    /**
     * @param e
     */
    public NestedRuntimeException(Throwable e) {
        this.exception = e;
        this.delegate = new NestedExceptionDelegate(null, e);
    }
    
    /**
     * @param e
     * @param message 自定义错误信息
     * @param args 占位符参数--[ 变长参数，用于替换message字符串里面的占位符"{}" ]
     */
    public NestedRuntimeException(Throwable e, String message, String... args) {
        this.exception = e;
        this.delegate = new NestedExceptionDelegate(null, e, message, args);
    }
    
    @Override
    public String getStackTraceStr() {
        return delegate.getStackTraceStr(null);
    }
    
    @Override
    public String getMessage() {
        return delegate.getMessage();
    }

    @Override
    public void printStackTrace() {
        delegate.printStackTrace();
    }

    @Override
    public void printStackTrace(PrintWriter err) {
        delegate.printStackTrace(err);
    }

    @Override
    public void printStackTrace(PrintStream err) {
        delegate.printStackTrace(err);
    }

    @Override
    public StackTraceElement[] getStackTrace() {
        if (null != exception) {
            return exception.getStackTrace();
        }
        return super.getStackTrace();
    }

    @Override
    public String toString() {
        return delegate.toString();
    }

    /**
     * 获取最原始的那个异常对象
     */
    @Override
    public Throwable getCause() {
        return delegate.getCause();
    }
    
    @Override
    public NestedExceptionDelegate getDelegate() {
        return delegate;
    }

}