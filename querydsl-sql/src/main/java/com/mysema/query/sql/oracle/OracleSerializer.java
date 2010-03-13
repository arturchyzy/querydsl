/*
 * Copyright (c) 2010 Mysema Ltd.
 * All rights reserved.
 * 
 */
package com.mysema.query.sql.oracle;

import com.mysema.query.sql.SQLSerializer;
import com.mysema.query.sql.SQLTemplates;
import com.mysema.query.types.expr.EBoolean;
import com.mysema.query.types.expr.Expr;

/**
 * OracleSerializer extended the SqlSerializer to support Oracle specific
 * constructs
 * 
 * @author tiwe
 * @version $Id$
 */
public final class OracleSerializer extends SQLSerializer {
    
    private static final String CONNECT_BY = "\nconnect by ";
    
    private static final String CONNECT_BY_NOCYCLE_PRIOR = "\nconnect by nocycle prior ";
    
    private static final String CONNECT_BY_PRIOR = "\nconnect by prior ";
    
    private static final String ORDER_SIBLINGS_BY = "\norder siblings by ";
    
    private static final String START_WITH = "\nstart with ";

    private final EBoolean connectBy, connectByPrior, connectByNocyclePrior;

    private final Expr<?> orderSiblingsBy;

    private final EBoolean startWith;

    public OracleSerializer(
            SQLTemplates patterns, 
            EBoolean connectBy,
            EBoolean connectByNocyclePrior, 
            EBoolean connectByPrior,
            Expr<?> orderSiblingsBy, 
            EBoolean startWith) {
        super(patterns);
        this.connectBy = connectBy;
        this.connectByNocyclePrior = connectByNocyclePrior;
        this.connectByPrior = connectByPrior;
        this.orderSiblingsBy = orderSiblingsBy;
        this.startWith = startWith;
    }

    @Override
    protected void beforeOrderBy() {
        if (startWith != null){
            append(START_WITH).handle(startWith);
        }            
        if (connectBy != null){
            append(CONNECT_BY).handle(connectBy);
        }            
        if (connectByPrior != null){
            append(CONNECT_BY_PRIOR).handle(connectByPrior);
        }            
        if (connectByNocyclePrior != null){
            append(CONNECT_BY_NOCYCLE_PRIOR).handle(connectByNocyclePrior);
        }            
        if (orderSiblingsBy != null){
            append(ORDER_SIBLINGS_BY).handle(orderSiblingsBy);
        }            
    }
}