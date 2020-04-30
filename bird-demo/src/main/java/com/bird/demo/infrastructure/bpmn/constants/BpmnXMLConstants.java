package com.bird.demo.infrastructure.bpmn.constants;

/**
 * @author Tijs Rademakers
 */
public interface BpmnXMLConstants {

    /**
     * custom
     */
    String BPMN_DEF_ID_ATTR = "id";
    String BPMN_DEF_ID_VALUE = "Definition";
    String TYPE_LANGUAGE_ATTRIBUTE_VALUE = "http://www.java.com/javaTypes";
    String EXPRESSION_LANGUAGE_ATTRIBUTE_VALUE = "http://www.mvel.org/2.0";

    /**
     * activiti constants
     */
    String BPMN2_NAMESPACE = "http://www.omg.org/spec/BPMN/20100524/MODEL";
    String XSI_NAMESPACE = "http://www.w3.org/2001/XMLSchema-instance";
    String XSI_PREFIX = "xsi";
    String SCHEMA_NAMESPACE = "http://www.w3.org/2001/XMLSchema";
    String XSD_PREFIX = "xsd";
    String TYPE_LANGUAGE_ATTRIBUTE = "typeLanguage";
    String XPATH_NAMESPACE = "http://www.w3.org/1999/XPath";
    String EXPRESSION_LANGUAGE_ATTRIBUTE = "expressionLanguage";
    String PROCESS_NAMESPACE = "http://www.jboss.org/drools";
    String PROCESS_NAMESPACE_OLD = "http://www.flowable.org/test";
    String TARGET_NAMESPACE_ATTRIBUTE = "targetNamespace";
    String FLOWABLE_EXTENSIONS_NAMESPACE = "http://www.jboss.org/drools";
    String FLOWABLE_EXTENSIONS_PREFIX = "tns";
    String FLOWABLE_EXTENSIONS_NAMESPACE_OLD = "http://flowable.org/bpmn";
    String FLOWABLE_EXTENSIONS_PREFIX_OLD = "flowable";
    String ACTIVITI_EXTENSIONS_NAMESPACE = "http://activiti.org/bpmn";
    String ACTIVITI_EXTENSIONS_PREFIX = "activiti";
    String BPMNDI_NAMESPACE = "http://www.omg.org/spec/BPMN/20100524/DI";
    String BPMNDI_PREFIX = "bpmndi";
    String OMGDC_NAMESPACE = "http://www.omg.org/spec/DD/20100524/DC";
    String OMGDC_PREFIX = "dc";
    String OMGDC_PREFIX_OLD = "omgdc";
    String OMGDI_NAMESPACE = "http://www.omg.org/spec/DD/20100524/DI";
    String OMGDI_PREFIX = "di";
    String OMGDI_PREFIX_OLD = "omgdi";

    String ATTRIBUTE_ID = "id";
    String ATTRIBUTE_NAME = "name";
    String ATTRIBUTE_TYPE = "type";
    String ATTRIBUTE_DEFAULT = "default";
    String ATTRIBUTE_ITEM_REF = "itemRef";
    String ELEMENT_DEFINITIONS = "definitions";
    String ELEMENT_DOCUMENTATION = "documentation";

    String ELEMENT_SIGNAL = "signal";
    String ELEMENT_MESSAGE = "message";
    String ELEMENT_ERROR = "error";
    String ELEMENT_ESCALATION = "escalation";
    String ELEMENT_COLLABORATION = "collaboration";
    String ELEMENT_PARTICIPANT = "participant";
    String ELEMENT_MESSAGE_FLOW = "messageFlow";
    String ELEMENT_LANESET = "laneSet";
    String ELEMENT_LANE = "lane";
    String ELEMENT_FLOWNODE_REF = "flowNodeRef";
    String ATTRIBUTE_PROCESS_REF = "processRef";
    String ELEMENT_RESOURCE = "resource";

    String ELEMENT_PROCESS = "process";
    String ATTRIBUTE_PROCESS_EXECUTABLE = "isExecutable";
    String ATTRIBUTE_PROCESS_EAGER_EXECUTION_FETCHING = "isEagerExecutionFetching";
    String ELEMENT_POTENTIAL_STARTER = "potentialStarter";
    String ATTRIBUTE_PROCESS_CANDIDATE_USERS = "candidateStarterUsers";
    String ATTRIBUTE_PROCESS_CANDIDATE_GROUPS = "candidateStarterGroups";
    String ELEMENT_SUBPROCESS = "subProcess";
    String ATTRIBUTE_TRIGGERED_BY = "triggeredByEvent";
    String ELEMENT_TRANSACTION = "transaction";
    String ELEMENT_ADHOC_SUBPROCESS = "adHocSubProcess";
    String ATTRIBUTE_ORDERING = "ordering";
    String ATTRIBUTE_CANCEL_REMAINING_INSTANCES = "cancelRemainingInstances";
    String ELEMENT_COMPLETION_CONDITION = "completionCondition";
    String ATTRIBUTE_MESSAGE_EXPRESSION = "messageExpression";
    String ATTRIBUTE_SIGNAL_EXPRESSION = "signalExpression";

    String ELEMENT_DATA_STATE = "dataState";

    String ELEMENT_EXTENSIONS = "extensionElements";

    String ELEMENT_EXECUTION_LISTENER = "executionListener";
    String ELEMENT_EVENT_LISTENER = "eventListener";
    String ELEMENT_TASK_LISTENER = "taskListener";
    String ATTRIBUTE_LISTENER_EVENT = "event";
    String ATTRIBUTE_LISTENER_EVENTS = "events";
    String ATTRIBUTE_LISTENER_ENTITY_TYPE = "entityType";
    String ATTRIBUTE_LISTENER_CLASS = "class";
    String ATTRIBUTE_LISTENER_EXPRESSION = "expression";
    String ATTRIBUTE_LISTENER_DELEGATEEXPRESSION = "delegateExpression";
    String ATTRIBUTE_LISTENER_THROW_EVENT_TYPE = "throwEvent";
    String ATTRIBUTE_LISTENER_THROW_SIGNAL_EVENT_NAME = "signalName";
    String ATTRIBUTE_LISTENER_THROW_MESSAGE_EVENT_NAME = "messageName";
    String ATTRIBUTE_LISTENER_THROW_ERROR_EVENT_CODE = "errorCode";
    String ATTRIBUTE_LISTENER_ON_TRANSACTION = "onTransaction";
    String ATTRIBUTE_LISTENER_CUSTOM_PROPERTIES_RESOLVER_CLASS = "customPropertiesResolverClass";
    String ATTRIBUTE_LISTENER_CUSTOM_PROPERTIES_RESOLVER_EXPRESSION = "customPropertiesResolverExpression";
    String ATTRIBUTE_LISTENER_CUSTOM_PROPERTIES_RESOLVER_DELEGATEEXPRESSION = "customPropertiesResolverDelegateExpression";

    String ELEMENT_HTTP_REQUEST_HANDLER = "httpRequestHandler";
    String ELEMENT_HTTP_RESPONSE_HANDLER = "httpResponseHandler";

    String ATTRIBUTE_LISTENER_THROW_EVENT_TYPE_SIGNAL = "signal";
    String ATTRIBUTE_LISTENER_THROW_EVENT_TYPE_GLOBAL_SIGNAL = "globalSignal";
    String ATTRIBUTE_LISTENER_THROW_EVENT_TYPE_MESSAGE = "message";
    String ATTRIBUTE_LISTENER_THROW_EVENT_TYPE_ERROR = "error";

    String ATTRIBUTE_VALUE_TRUE = "true";
    String ATTRIBUTE_VALUE_FALSE = "false";

    String ATTRIBUTE_ACTIVITY_ASYNCHRONOUS = "async";
    String ATTRIBUTE_ACTIVITY_EXCLUSIVE = "exclusive";
    String ATTRIBUTE_ACTIVITY_ISFORCOMPENSATION = "isForCompensation";
    String ATTRIBUTE_ACTIVITY_TRIGGERABLE = "triggerable";

    String ELEMENT_IMPORT = "import";
    String ATTRIBUTE_IMPORT_TYPE = "importType";
    String ATTRIBUTE_LOCATION = "location";
    String ATTRIBUTE_NAMESPACE = "namespace";

    String ELEMENT_INTERFACE = "interface";
    String ELEMENT_OPERATION = "operation";
    String ATTRIBUTE_IMPLEMENTATION_REF = "implementationRef";
    String ELEMENT_IN_MESSAGE = "inMessageRef";
    String ELEMENT_OUT_MESSAGE = "outMessageRef";

    String ELEMENT_ITEM_DEFINITION = "itemDefinition";
    String ATTRIBUTE_STRUCTURE_REF = "structureRef";
    String ATTRIBUTE_ITEM_KIND = "itemKind";

    String ELEMENT_DATA_STORE = "dataStore";
    String ELEMENT_DATA_STORE_REFERENCE = "dataStoreReference";
    String ATTRIBUTE_ITEM_SUBJECT_REF = "itemSubjectRef";
    String ATTRIBUTE_DATA_STORE_REF = "dataStoreRef";

    String ELEMENT_IOSPECIFICATION = "ioSpecification";
    String ELEMENT_DATA_INPUT = "dataInput";
    String ELEMENT_DATA_OUTPUT = "dataOutput";
    String ELEMENT_DATA_INPUT_REFS = "dataInputRefs";
    String ELEMENT_DATA_OUTPUT_REFS = "dataOutputRefs";

    String ELEMENT_INPUT_ASSOCIATION = "dataInputAssociation";
    String ELEMENT_OUTPUT_ASSOCIATION = "dataOutputAssociation";
    String ELEMENT_SOURCE_REF = "sourceRef";
    String ELEMENT_TARGET_REF = "targetRef";
    String ELEMENT_TRANSFORMATION = "transformation";
    String ELEMENT_ASSIGNMENT = "assignment";
    String ELEMENT_FROM = "from";
    String ELEMENT_TO = "to";

    // fake element for mail task
    String ELEMENT_TASK_MAIL = "mailTask";

    String ELEMENT_TASK = "task";
    String ELEMENT_TASK_BUSINESSRULE = "businessRuleTask";
    String ELEMENT_TASK_MANUAL = "manualTask";
    String ELEMENT_TASK_RECEIVE = "receiveTask";
    String ELEMENT_TASK_SCRIPT = "scriptTask";
    String ELEMENT_TASK_SEND = "sendTask";
    String ELEMENT_TASK_SERVICE = "serviceTask";
    String ELEMENT_TASK_USER = "userTask";
    String ELEMENT_CALL_ACTIVITY = "callActivity";

    String ATTRIBUTE_EVENT_START_INITIATOR = "initiator";
    String ATTRIBUTE_EVENT_START_INTERRUPTING = "isInterrupting";
    String ATTRIBUTE_FORM_FORMKEY = "formKey";
    String ATTRIBUTE_FORM_FIELD_VALIDATION = "formFieldValidation";

    String ELEMENT_MULTIINSTANCE = "multiInstanceLoopCharacteristics";
    String ELEMENT_MULTIINSTANCE_CARDINALITY = "loopCardinality";
    String ELEMENT_MULTIINSTANCE_DATAINPUT = "loopDataInputRef";
    String ELEMENT_MULTIINSTANCE_DATAITEM = "inputDataItem";
    String ELEMENT_MULTIINSTANCE_COLLECTION = "collection";
    String ELEMENT_MULTIINSTANCE_COLLECTION_EXPRESSION = "expression";
    String ELEMENT_MULTIINSTANCE_COLLECTION_STRING = "string";
    String ELEMENT_MULTIINSTANCE_CONDITION = "completionCondition";
    String ATTRIBUTE_MULTIINSTANCE_SEQUENTIAL = "isSequential";
    String ATTRIBUTE_MULTIINSTANCE_COLLECTION = "collection";
    String ATTRIBUTE_MULTIINSTANCE_VARIABLE = "elementVariable";
    String ATTRIBUTE_MULTIINSTANCE_INDEX_VARIABLE = "elementIndexVariable";
    String ATTRIBUTE_MULTIINSTANCE_COLLECTION_CLASS = "class";
    String ATTRIBUTE_MULTIINSTANCE_COLLECTION_DELEGATEEXPRESSION = "delegateExpression";

    String ATTRIBUTE_TASK_IMPLEMENTATION = "implementation";
    String ATTRIBUTE_TASK_OPERATION_REF = "operationRef";

    String ATTRIBUTE_TASK_SCRIPT_TEXT = "script";
    String ATTRIBUTE_TASK_SCRIPT_FORMAT = "scriptFormat";
    String ATTRIBUTE_TASK_SCRIPT_RESULTVARIABLE = "resultVariable";
    String ATTRIBUTE_TASK_SCRIPT_AUTO_STORE_VARIABLE = "autoStoreVariables";

    String ATTRIBUTE_TASK_SERVICE_CLASS = "class";
    String ATTRIBUTE_TASK_SERVICE_EXPRESSION = "expression";
    String ATTRIBUTE_TASK_SERVICE_DELEGATEEXPRESSION = "delegateExpression";
    String ATTRIBUTE_TASK_SERVICE_RESULTVARIABLE = "resultVariableName";
    String ATTRIBUTE_TASK_SERVICE_EXTENSIONID = "extensionId";
    String ATTRIBUTE_TASK_SERVICE_SKIP_EXPRESSION = "skipExpression";
    String ATTRIBUTE_TASK_SERVICE_USE_LOCAL_SCOPE_FOR_RESULT_VARIABLE = "useLocalScopeForResultVariable";

    String ATTRIBUTE_TASK_USER_ASSIGNEE = "assignee";
    String ATTRIBUTE_TASK_USER_OWNER = "owner";
    String ATTRIBUTE_TASK_USER_CANDIDATEUSERS = "candidateUsers";
    String ATTRIBUTE_TASK_USER_CANDIDATEGROUPS = "candidateGroups";
    String ATTRIBUTE_TASK_USER_DUEDATE = "dueDate";
    String ATTRIBUTE_TASK_USER_BUSINESS_CALENDAR_NAME = "businessCalendarName";
    String ATTRIBUTE_TASK_USER_CATEGORY = "category";
    String ATTRIBUTE_TASK_USER_PRIORITY = "priority";
    String ATTRIBUTE_TASK_USER_SKIP_EXPRESSION = "skipExpression";

    String ATTRIBUTE_TASK_RULE_VARIABLES_INPUT = "ruleVariablesInput";
    String ATTRIBUTE_TASK_RULE_RESULT_VARIABLE = "resultVariable";
    String ATTRIBUTE_TASK_RULE_RULES = "ruleFlowGroup";
    String ATTRIBUTE_TASK_RULE_EXCLUDE = "exclude";
    String ATTRIBUTE_TASK_RULE_CLASS = "class";

    String ATTRIBUTE_BUSINESS_KEY = "businessKey";
    String ATTRIBUTE_INHERIT_BUSINESS_KEY = "inheritBusinessKey";
    String ATTRIBUTE_SAME_DEPLOYMENT = "sameDeployment";
    String ATTRIBUTE_FALLBACK_TO_DEFAULT_TENANT = "fallbackToDefaultTenant";
    String ELEMENT_IN_PARAMETERS = "in";
    String ELEMENT_OUT_PARAMETERS = "out";

    String ATTRIBUTE_CALL_ACTIVITY_CALLEDELEMENT = "calledElement";
    String ATTRIBUTE_CALL_ACTIVITY_CALLEDELEMENTTYPE = "calledElementType";
    String ATTRIBUTE_CALL_ACTIVITY_PROCESS_INSTANCE_NAME = "processInstanceName";
    String ATTRIBUTE_CALL_ACTIVITY_INHERITVARIABLES = "inheritVariables";
    String ATTRIBUTE_CALL_ACTIVITY_USE_LOCALSCOPE_FOR_OUTPARAMETERS = "useLocalScopeForOutParameters";
    String ATTRIBUTE_CALL_ACTIVITY_COMPLETE_ASYNC = "completeAsync";
    String ATTRIBUTE_IOPARAMETER_SOURCE = "source";
    String ATTRIBUTE_IOPARAMETER_SOURCE_EXPRESSION = "sourceExpression";
    String ATTRIBUTE_IOPARAMETER_TARGET = "target";

    String ATTRIBUTE_CASE_TASK_CASE_DEFINITION_KEY = "caseDefinitionKey";
    String ATTRIBUTE_CASE_TASK_CASE_INSTANCE_NAME = "caseInstanceName";

    String ELEMENT_SEQUENCE_FLOW = "sequenceFlow";
    String ELEMENT_FLOW_CONDITION = "conditionExpression";
    String ATTRIBUTE_FLOW_SOURCE_REF = "sourceRef";
    String ATTRIBUTE_FLOW_TARGET_REF = "targetRef";
    String ATTRIBUTE_FLOW_SKIP_EXPRESSION = "skipExpression";

    String ELEMENT_TEXT_ANNOTATION = "textAnnotation";
    String ATTRIBUTE_TEXTFORMAT = "textFormat";
    String ELEMENT_TEXT_ANNOTATION_TEXT = "text";

    String ELEMENT_ASSOCIATION = "association";

    String ELEMENT_GATEWAY_EXCLUSIVE = "exclusiveGateway";
    String ELEMENT_GATEWAY_EVENT = "eventBasedGateway";
    String ELEMENT_GATEWAY_INCLUSIVE = "inclusiveGateway";
    String ELEMENT_GATEWAY_PARALLEL = "parallelGateway";
    String ELEMENT_GATEWAY_COMPLEX = "complexGateway";

    String ELEMENT_EVENT_START = "startEvent";
    String ELEMENT_EVENT_END = "endEvent";
    String ELEMENT_EVENT_BOUNDARY = "boundaryEvent";
    String ELEMENT_EVENT_THROW = "intermediateThrowEvent";
    String ELEMENT_EVENT_CATCH = "intermediateCatchEvent";

    String ATTRIBUTE_BOUNDARY_ATTACHEDTOREF = "attachedToRef";
    String ATTRIBUTE_BOUNDARY_CANCELACTIVITY = "cancelActivity";

    String ELEMENT_EVENT_CONDITIONALDEFINITION = "conditionalEventDefinition";
    String ELEMENT_CONDITION = "condition";

    String ELEMENT_EVENT_ERRORDEFINITION = "errorEventDefinition";
    String ATTRIBUTE_ERROR_REF = "errorRef";
    String ATTRIBUTE_ERROR_CODE = "errorCode";

    String ELEMENT_EVENT_MESSAGEDEFINITION = "messageEventDefinition";
    String ATTRIBUTE_MESSAGE_REF = "messageRef";

    String ELEMENT_EVENT_SIGNALDEFINITION = "signalEventDefinition";
    String ATTRIBUTE_SIGNAL_REF = "signalRef";
    String ATTRIBUTE_SCOPE = "scope";

    String ELEMENT_EVENT_TIMERDEFINITION = "timerEventDefinition";
    String ATTRIBUTE_CALENDAR_NAME = "businessCalendarName";
    String ATTRIBUTE_TIMER_DATE = "timeDate";
    String ATTRIBUTE_TIMER_CYCLE = "timeCycle";
    String ATTRIBUTE_END_DATE = "endDate";
    String ATTRIBUTE_TIMER_DURATION = "timeDuration";

    String ELEMENT_EVENT_ESCALATIONDEFINITION = "escalationEventDefinition";
    String ATTRIBUTE_ESCALATION_REF = "escalationRef";
    String ATTRIBUTE_ESCALATION_CODE = "escalationCode";

    String ELEMENT_EVENT_TERMINATEDEFINITION = "terminateEventDefinition";
    String ATTRIBUTE_TERMINATE_ALL = "terminateAll";
    String ATTRIBUTE_TERMINATE_MULTI_INSTANCE = "terminateMultiInstance";

    String ELEMENT_EVENT_CANCELDEFINITION = "cancelEventDefinition";

    String ELEMENT_EVENT_COMPENSATEDEFINITION = "compensateEventDefinition";
    String ATTRIBUTE_COMPENSATE_ACTIVITYREF = "activityRef";
    String ATTRIBUTE_COMPENSATE_WAITFORCOMPLETION = "waitForCompletion";

    String ELEMENT_FORMPROPERTY = "formProperty";
    String ATTRIBUTE_FORM_ID = "id";
    String ATTRIBUTE_FORM_NAME = "name";
    String ATTRIBUTE_FORM_TYPE = "type";
    String ATTRIBUTE_FORM_EXPRESSION = "expression";
    String ATTRIBUTE_FORM_VARIABLE = "variable";
    String ATTRIBUTE_FORM_READABLE = "readable";
    String ATTRIBUTE_FORM_WRITABLE = "writable";
    String ATTRIBUTE_FORM_REQUIRED = "required";
    String ATTRIBUTE_FORM_DEFAULT = "default";
    String ATTRIBUTE_FORM_DATEPATTERN = "datePattern";
    String ELEMENT_VALUE = "value";

    String ELEMENT_FIELD = "field";
    String ATTRIBUTE_FIELD_NAME = "name";
    String ATTRIBUTE_FIELD_STRING = "stringValue";
    String ATTRIBUTE_FIELD_EXPRESSION = "expression";
    String ELEMENT_FIELD_STRING = "string";

    String ALFRESCO_TYPE = "alfrescoScriptType";

    String ELEMENT_DI_DIAGRAM = "BPMNDiagram";
    String ELEMENT_DI_PLANE = "BPMNPlane";
    String ELEMENT_DI_SHAPE = "BPMNShape";
    String ELEMENT_DI_EDGE = "BPMNEdge";
    String ELEMENT_DI_LABEL = "BPMNLabel";
    String ELEMENT_DI_BOUNDS = "Bounds";
    String ELEMENT_DI_WAYPOINT = "waypoint";
    String ATTRIBUTE_DI_BPMNELEMENT = "bpmnElement";
    String ATTRIBUTE_DI_IS_EXPANDED = "isExpanded";
    String ATTRIBUTE_DI_WIDTH = "width";
    String ATTRIBUTE_DI_HEIGHT = "height";
    String ATTRIBUTE_DI_X = "x";
    String ATTRIBUTE_DI_Y = "y";

    String ELEMENT_DATA_OBJECT = "dataObject";
    String ATTRIBUTE_DATA_ID = "id";
    String ATTRIBUTE_DATA_NAME = "name";
    String ATTRIBUTE_DATA_ITEM_REF = "itemSubjectRef";
    // only used by valued data objects
    String ELEMENT_DATA_VALUE = "value";

    String ELEMENT_CUSTOM_RESOURCE = "customResource";
    String ELEMENT_RESOURCE_ASSIGNMENT = "resourceAssignmentExpression";
    String ELEMENT_FORMAL_EXPRESSION = "formalExpression";
    String ELEMENT_RESOURCE_REF = "resourceRef";
    String ATTRIBUTE_ASSOCIATION_DIRECTION = "associationDirection";

    String FAILED_JOB_RETRY_TIME_CYCLE = "failedJobRetryTimeCycle";
    String MAP_EXCEPTION = "mapException";
    String MAP_EXCEPTION_ERRORCODE = "errorCode";
    String MAP_EXCEPTION_ANDCHILDREN = "includeChildExceptions";
    String MAP_EXCEPTION_ROOTCAUSE = "rootCause";


}
