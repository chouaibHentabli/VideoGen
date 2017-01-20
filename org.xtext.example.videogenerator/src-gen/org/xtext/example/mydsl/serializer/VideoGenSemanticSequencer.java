/*
 * generated by Xtext
 */
package org.xtext.example.mydsl.serializer;

import com.google.inject.Inject;
import java.util.Set;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Parameter;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.serializer.ISerializationContext;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;
import org.xtext.example.mydsl.services.VideoGenGrammarAccess;
import org.xtext.example.mydsl.videoGen.AlternativeVideoSeq;
import org.xtext.example.mydsl.videoGen.MandatoryVideoSeq;
import org.xtext.example.mydsl.videoGen.OptionalVideoSeq;
import org.xtext.example.mydsl.videoGen.VideoDescription;
import org.xtext.example.mydsl.videoGen.VideoGenInformation;
import org.xtext.example.mydsl.videoGen.VideoGenPackage;
import org.xtext.example.mydsl.videoGen.VideoGeneratorModel;

@SuppressWarnings("all")
public class VideoGenSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private VideoGenGrammarAccess grammarAccess;
	
	@Override
	public void sequence(ISerializationContext context, EObject semanticObject) {
		EPackage epackage = semanticObject.eClass().getEPackage();
		ParserRule rule = context.getParserRule();
		Action action = context.getAssignedAction();
		Set<Parameter> parameters = context.getEnabledBooleanParameters();
		if (epackage == VideoGenPackage.eINSTANCE)
			switch (semanticObject.eClass().getClassifierID()) {
			case VideoGenPackage.ALTERNATIVE_VIDEO_SEQ:
				sequence_AlternativeVideoSeq(context, (AlternativeVideoSeq) semanticObject); 
				return; 
			case VideoGenPackage.MANDATORY_VIDEO_SEQ:
				sequence_MandatoryVideoSeq(context, (MandatoryVideoSeq) semanticObject); 
				return; 
			case VideoGenPackage.OPTIONAL_VIDEO_SEQ:
				sequence_OptionalVideoSeq(context, (OptionalVideoSeq) semanticObject); 
				return; 
			case VideoGenPackage.VIDEO_DESCRIPTION:
				sequence_VideoDescription(context, (VideoDescription) semanticObject); 
				return; 
			case VideoGenPackage.VIDEO_GEN_INFORMATION:
				sequence_VideoGenInformation(context, (VideoGenInformation) semanticObject); 
				return; 
			case VideoGenPackage.VIDEO_GENERATOR_MODEL:
				sequence_VideoGeneratorModel(context, (VideoGeneratorModel) semanticObject); 
				return; 
			}
		if (errorAcceptor != null)
			errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * Contexts:
	 *     VideoSeq returns AlternativeVideoSeq
	 *     AlternativeVideoSeq returns AlternativeVideoSeq
	 *
	 * Constraint:
	 *     (videoid=ID? videodescs+=VideoDescription+)
	 */
	protected void sequence_AlternativeVideoSeq(ISerializationContext context, AlternativeVideoSeq semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     VideoSeq returns MandatoryVideoSeq
	 *     MandatoryVideoSeq returns MandatoryVideoSeq
	 *
	 * Constraint:
	 *     description=VideoDescription
	 */
	protected void sequence_MandatoryVideoSeq(ISerializationContext context, MandatoryVideoSeq semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, VideoGenPackage.Literals.MANDATORY_VIDEO_SEQ__DESCRIPTION) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, VideoGenPackage.Literals.MANDATORY_VIDEO_SEQ__DESCRIPTION));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getMandatoryVideoSeqAccess().getDescriptionVideoDescriptionParserRuleCall_1_0(), semanticObject.getDescription());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     VideoSeq returns OptionalVideoSeq
	 *     OptionalVideoSeq returns OptionalVideoSeq
	 *
	 * Constraint:
	 *     description=VideoDescription
	 */
	protected void sequence_OptionalVideoSeq(ISerializationContext context, OptionalVideoSeq semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, VideoGenPackage.Literals.OPTIONAL_VIDEO_SEQ__DESCRIPTION) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, VideoGenPackage.Literals.OPTIONAL_VIDEO_SEQ__DESCRIPTION));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getOptionalVideoSeqAccess().getDescriptionVideoDescriptionParserRuleCall_1_0(), semanticObject.getDescription());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     VideoDescription returns VideoDescription
	 *
	 * Constraint:
	 *     (
	 *         videoid=ID? 
	 *         location=STRING 
	 *         duration=INT? 
	 *         probability=INT? 
	 *         size=INT? 
	 *         textIncrust=STRING? 
	 *         filter=STRING? 
	 *         description=STRING?
	 *     )
	 */
	protected void sequence_VideoDescription(ISerializationContext context, VideoDescription semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     VideoGenInformation returns VideoGenInformation
	 *
	 * Constraint:
	 *     (authorName=STRING version=STRING? creationDate=STRING?)
	 */
	protected void sequence_VideoGenInformation(ISerializationContext context, VideoGenInformation semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     VideoGeneratorModel returns VideoGeneratorModel
	 *
	 * Constraint:
	 *     (information=VideoGenInformation? videoseqs+=VideoSeq+)
	 */
	protected void sequence_VideoGeneratorModel(ISerializationContext context, VideoGeneratorModel semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
}
