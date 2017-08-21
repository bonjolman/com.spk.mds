/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.db.entity.lib;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author WILDAN
 */
@Entity
@Table(name = "config")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Config.findAll", query = "SELECT c FROM Config c"),
    @NamedQuery(name = "Config.findByIdConfig", query = "SELECT c FROM Config c WHERE c.idConfig = :idConfig"),
    @NamedQuery(name = "Config.findByName", query = "SELECT c FROM Config c WHERE c.name = :name"),
    @NamedQuery(name = "Config.findByValue", query = "SELECT c FROM Config c WHERE c.value = :value"),
    @NamedQuery(name = "Config.findByDescription", query = "SELECT c FROM Config c WHERE c.description = :description")})
public class Config implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_config")
    private String idConfig;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "value")
    private String value;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;

    public Config() {
    }

    public Config(String idConfig) {
        this.idConfig = idConfig;
    }

    public Config(String idConfig, String name, String value, String description) {
        this.idConfig = idConfig;
        this.name = name;
        this.value = value;
        this.description = description;
    }

    public String getIdConfig() {
        return idConfig;
    }

    public void setIdConfig(String idConfig) {
        this.idConfig = idConfig;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConfig != null ? idConfig.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Config)) {
            return false;
        }
        Config other = (Config) object;
        if ((this.idConfig == null && other.idConfig != null) || (this.idConfig != null && !this.idConfig.equals(other.idConfig))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.db.entity.lib.Config[ idConfig=" + idConfig + " ]";
    }
    
}
