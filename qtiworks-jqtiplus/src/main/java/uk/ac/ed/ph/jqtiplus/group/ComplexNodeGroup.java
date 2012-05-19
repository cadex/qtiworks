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
package uk.ac.ed.ph.jqtiplus.group;

import uk.ac.ed.ph.jqtiplus.node.XmlNode;

import java.util.Set;

/**
 * Parent of all groups.
 *
 * @author Jiri Kajaba
 */
public abstract class ComplexNodeGroup<P extends XmlNode, C extends XmlNode> extends AbstractNodeGroup<P,C> {

    private static final long serialVersionUID = 903238011893494959L;

    /**
     * Constructs group with maximum set to 1.
     * <p>
     * This is convenient constructor for group with only one child.
     *
     * @param parent parent of created group
     * @param name name of created group
     * @param required if true, minimum is set to 1, if false, minimum is set to 0
     */
    public ComplexNodeGroup(final P parent, final String name, final Set<String> supportedQtiClasses, final boolean required) {
        super(parent, name, supportedQtiClasses, required ? 1 : 0, 1);
    }

    public ComplexNodeGroup(final P parent, final String name, final Set<String> supportedQtiClasses,
            final Integer minimum, final Integer maximum) {
        super(parent, name, supportedQtiClasses, minimum, maximum);
    }

    @Override
    public final boolean isComplexContent() {
        return true;
    }
}
