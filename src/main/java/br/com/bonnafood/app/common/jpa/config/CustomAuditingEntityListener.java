package br.com.bonnafood.app.common.jpa.config;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.data.auditing.AuditingHandler;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Component
@NoArgsConstructor
public class CustomAuditingEntityListener  {
    private ObjectFactory<AuditingHandler> handler;

    public CustomAuditingEntityListener(ObjectFactory<AuditingHandler> handler) {
        this.handler = handler;
    }
    @PrePersist
    public void touchForCreate(Object target) {
        if (handler != null) {
            AuditingHandler object = handler.getObject();
            object.markCreated(target);
        }
    }

    @PreUpdate
    public void touchForUpdate(Object target) {
        if (handler != null) {
//            não fazer nada até resolver problema
//            AuditingHandler object = handler.getObject();
//            object.markModified(target);
        }

    }

}