package com.example.dao.core.pool;


import com.example.dao.core.pool.connection.ProxyConnection;

public interface ConnectionPool {

    ProxyConnection getConnection();

    void putBackConnection(ProxyConnection connection);

    void destroyPool();
}

