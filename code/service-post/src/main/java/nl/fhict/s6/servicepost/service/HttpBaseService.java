package nl.fhict.s6.servicepost.service;

import nl.fhict.s6.servicepost.context.BaseHttpContext;

public class HttpBaseService<T,Id> {
    private BaseHttpContext<T,Id> baseHttpContext;
    public HttpBaseService(BaseHttpContext<T,Id> baseHttpContext) {
        this.baseHttpContext = baseHttpContext;
    }
    public T getById(Id id)
    {
        return baseHttpContext.getDtoById(id);
    }
}
