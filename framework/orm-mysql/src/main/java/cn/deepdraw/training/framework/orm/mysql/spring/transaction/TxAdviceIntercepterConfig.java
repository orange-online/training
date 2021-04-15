package cn.deepdraw.training.framework.orm.mysql.spring.transaction;

//@Order(1)
//@Configuration
//@EnableAspectJAutoProxy
//@AutoConfigureAfter({ ApplicationPersistenceConfiguration.class })
public class TxAdviceIntercepterConfig {
//
//	private static final int TX_METHOD_TIMEOUT = 5;
//
//	private static final String AOP_POINTCUT_EXPRESSION = "execution(* cn.deepdraw.service..*Service.*(..))";
//
//	@Autowired
//	private PlatformTransactionManager transactionManager;
//
//	@Bean
//	public Advice txInterceptor() {
//
//		NameMatchTransactionAttributeSource attrSource = new NameMatchTransactionAttributeSource();
//		attrSource.setNameMap(getMethodName2TransactionAttributeMap());
//		return new TransactionInterceptor(transactionManager, attrSource);
//	}
//
//	private Map<String, TransactionAttribute> getMethodName2TransactionAttributeMap() {
//
//		RuleBasedTransactionAttribute readOnlyAttr = getReadOnlyTransactionAttribute();
//		RuleBasedTransactionAttribute requiredAttr = getRequiredTransactionAttribute();
//		Map<String, TransactionAttribute> name2TransactionAttributeMap = new HashMap<>();
//
//		// 对新增/修改/删除方法进行事务支持
//		name2TransactionAttributeMap.put("add*", requiredAttr);
//		name2TransactionAttributeMap.put("save*", requiredAttr);
//		name2TransactionAttributeMap.put("create*", requiredAttr);
//		name2TransactionAttributeMap.put("edit*", requiredAttr);
//		name2TransactionAttributeMap.put("update*", requiredAttr);
//		name2TransactionAttributeMap.put("delete*", requiredAttr);
//		name2TransactionAttributeMap.put("remove*", requiredAttr);
//
//		// 对其它方法(查找/搜索等)进行只读事务
//		name2TransactionAttributeMap.put("*", readOnlyAttr);
//		return name2TransactionAttributeMap;
//	}
//
//	/**
//	 * 只读事务，不做更新操作
//	 */
//	public RuleBasedTransactionAttribute getReadOnlyTransactionAttribute() {
//
//		RuleBasedTransactionAttribute readOnlyTransactionAttribute = new RuleBasedTransactionAttribute();
//		readOnlyTransactionAttribute.setReadOnly(true);
//		readOnlyTransactionAttribute.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);
//		return readOnlyTransactionAttribute;
//	}
//
//	/**
//	 * 当前存在事务就使用当前事务，当前不存在事务就创建一个新的事务
//	 */
//	public RuleBasedTransactionAttribute getRequiredTransactionAttribute() {
//
//		RuleBasedTransactionAttribute requiredTransactionAttribute = new RuleBasedTransactionAttribute();
//		requiredTransactionAttribute
//				.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
//		requiredTransactionAttribute.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
//		requiredTransactionAttribute.setTimeout(TX_METHOD_TIMEOUT);
//		return requiredTransactionAttribute;
//	}
//
//	@Bean
//	public Advisor txAdviceAdvisor() {
//
//		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
//		pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
//		return new DefaultPointcutAdvisor(pointcut, txInterceptor());
//	}
//
//	/** 切面拦截规则 参数会自动从容器中注入 */
//	@Bean
//	public AspectJExpressionPointcutAdvisor pointcutAdvisor() {
//
//		AspectJExpressionPointcutAdvisor pointcutAdvisor = new AspectJExpressionPointcutAdvisor();
//		pointcutAdvisor.setAdvice(txInterceptor());
//		pointcutAdvisor.setExpression(AOP_POINTCUT_EXPRESSION);
//		return pointcutAdvisor;
//	}
}