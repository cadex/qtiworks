/* Copyright (c) 2012, University of Edinburgh.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright notice, this
 *   list of conditions and the following disclaimer in the documentation and/or
 *   other materials provided with the distribution.
 *
 * * Neither the name of the University of Edinburgh nor the names of its
 *   contributors may be used to endorse or promote products derived from this
 *   software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *
 * This software is derived from (and contains code from) QTItools and MathAssessEngine.
 * QTItools is (c) 2008, University of Southampton.
 * MathAssessEngine is (c) 2010, University of Edinburgh.
 */
package uk.ac.ed.ph.qtiworks.domain.entities;

import uk.ac.ed.ph.qtiworks.QtiWorksLogicException;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Base class for the different types of Users we support
 * <p>
 * (This class is should be considered abstract, though has been made concrete to allow
 * the underlying JPA reflection magic to work correctly.)
 *
 * @author David McKain
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="users")
@SequenceGenerator(name="userSequence", sequenceName="user_sequence", initialValue=1000, allocationSize=10)
public class User implements BaseEntity, TimestampedOnCreation {

    private static final long serialVersionUID = -4310598861282271053L;

    @Id
    @GeneratedValue(generator="userSequence")
    @Column(name="uid")
    private Long id;

    @Basic(optional=false)
    @Column(name="creation_time",updatable=false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;

    @Basic(optional=false)
    @Column(name="login_disabled",updatable=true)
    private boolean loginDisabled;

    @Basic(optional=false)
    @Column(name="user_type",updatable=false,length=10)
    @Enumerated(EnumType.STRING)
    private UserType userType;

    //------------------------------------------------------------

    public User() {
        /* (Don't use this in code - required when creating instances by reflection) */
    }

    protected User(final UserType userType) {
        this.userType = userType;
    }

    //------------------------------------------------------------

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(final Long id) {
        this.id = id;
    }


    @Override
    public Date getCreationTime() {
        return creationTime;
    }

    @Override
    public void setCreationTime(final Date creationTime) {
        this.creationTime = creationTime;
    }


    public boolean isLoginDisabled() {
        return loginDisabled;
    }

    public void setLoginDisabled(final boolean loginDisabled) {
        this.loginDisabled = loginDisabled;
    }


    public UserType getUserType() {
        return userType;
    }

    protected void setUserType(final UserType userType) {
        this.userType = userType;
    }

    //------------------------------------------------------------

    public String getBusinessKey() {
        throw new QtiWorksLogicException("This must be filled in by a subclass");
    }

    //------------------------------------------------------------

    public boolean isInstructor() {
        return userType==UserType.INSTRUCTOR;
    }

    public boolean isAnonymous() {
        return userType==UserType.ANONYMOUS;
    }

    public boolean isSysAdmin() {
        return userType==UserType.INSTRUCTOR && ((InstructorUser) this).isSysAdmin();
    }

    //------------------------------------------------------------

    @Override
    public final String toString() {
        return getClass().getSimpleName() + "@" + Integer.toHexString(System.identityHashCode(this))
                + "(" + getBusinessKey() + ")";
    }

    @Override
    public final int hashCode() {
        return getBusinessKey().hashCode();
    }

    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }
        final User other = (User) obj;
        return getBusinessKey().equals(other.getBusinessKey());
    }
}