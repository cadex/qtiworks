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
package uk.ac.ed.ph.jqtiplus.running;

import uk.ac.ed.ph.jqtiplus.node.QtiNode;
import uk.ac.ed.ph.jqtiplus.node.test.AssessmentItemRef;
import uk.ac.ed.ph.jqtiplus.node.test.AssessmentTest;
import uk.ac.ed.ph.jqtiplus.state.TestPlanNode;
import uk.ac.ed.ph.jqtiplus.state.TestSessionState;
import uk.ac.ed.ph.jqtiplus.types.Identifier;
import uk.ac.ed.ph.jqtiplus.validation.TestValidationContext;
import uk.ac.ed.ph.jqtiplus.value.Value;

/**
 * Extension of {@link ProcessingContext} passed when running an {@link AssessmentTest}
 *
 * @author David McKain
 */
public interface TestProcessingContext extends ProcessingContext, TestValidationContext {

    /**
     * Returns the {@link TestSessionState} attached to this context.
     */
    TestSessionState getTestSessionState();

    /**
     * Callback used to handle dereferenced variables in tests.
     */
    public static interface DereferencedTestVariableHandler {

        /**
         * Called when a variable reference is dereferenced to a variable within the current test.
         *
         * @param testProcessingContext provides access to calling {@link TestProcessingContext}
         * @param testVariableIdentifier identifier of the resolved test variable
         */
        Value evaluateInThisTest(TestProcessingContext testProcessingContext, Identifier testVariableIdentifier);

        /**
         * Called when a variable reference is dereferenced to an item variable within a
         * referenced item.
         *
         * @param itemProcessingContext provides access to the {@link ItemProcessingContext} of
         *   the resulting instance of the referenced item
         * @param assessmentItemRef the {@link AssessmentItemRef} referencing the item
         * @param testPlanNode instance of the resulting {@link AssessmentItemRef} in the test plan
         * @param itemVariableIdentifier identifier of the item variable within the referenced item
         */
        Value evaluateInReferencedItem(final ItemProcessingContext itemProcessingContext,
                AssessmentItemRef assessmentItemRef, TestPlanNode testPlanNode,
                Identifier itemVariableIdentifier);
    }

    Value dereferenceVariable(QtiNode caller, Identifier referenceIdentifier,
            DereferencedTestVariableHandler dereferencedVariableHandler);

//    @Deprecated
//    AssessmentItemRefAttemptController getItemRefController(AssessmentItemRefState itemRefState);
//
//    @Deprecated
//    Map<AssessmentItemRefState, AssessmentItemRefAttemptController> getItemRefControllers(AssessmentItemRef itemRef);
//
//    @Deprecated
//    List<AssessmentItemRefState> lookupItemRefStates();
//
//    /** Called during outcome processing when there's a {@link QtiProcessingInterrupt} */
//    @Deprecated
//    void terminate();

}
