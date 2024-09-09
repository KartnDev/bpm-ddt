package com.company.bpmddt.entity;

import io.jmix.core.FileRef;
import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@JmixEntity
@Table(name = "DDT", indexes = {
        @Index(name = "IDX_DDT_TRANSPORT_ORDER", columnList = "TRANSPORT_ORDER_ID")
})
@Entity
public class DDT {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @Column(name = "NUMBER_")
    private String number;

    @Column(name = "DATE_")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "ATTACHED_FILE", length = 1024)
    private FileRef attachedFile;
    @JoinColumn(name = "TRANSPORT_ORDER_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private TransportOrder transportOrder;

    public TransportOrder getTransportOrder() {
        return transportOrder;
    }

    public void setTransportOrder(TransportOrder transportOrder) {
        this.transportOrder = transportOrder;
    }

    public FileRef getAttachedFile() {
        return attachedFile;
    }

    public void setAttachedFile(FileRef attachedFile) {
        this.attachedFile = attachedFile;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}