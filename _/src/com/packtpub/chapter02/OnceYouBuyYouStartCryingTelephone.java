package com.packtpub.chapter02;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class OnceYouBuyYouStartCryingTelephone {

	private static final String SPACE = " ";
	private Map<String, ConnectionType> connectionTypeForNumberMap = new HashMap<String, ConnectionType>();
	private Map<ConnectionType, PhoneConnection> connectionForATypeMap = new HashMap<ConnectionType, PhoneConnection>();
	private Map<String, Date> cd = new HashMap<String, Date>();

	public OnceYouBuyYouStartCryingTelephone() {
		initialize();
	}

	protected void initialize() {
		connectionForATypeMap.put(ConnectionType.TWO_G, new TwoGConnection());
		connectionForATypeMap.put(ConnectionType.THREE_G,
				new ThreeGConnection());
	}

	/**
	 * This method activates a connection for a person and stores values in
	 * following maps for future use names, types and cd if connection type is
	 * 2G then requests TRY for a valid 2G number. if portability is not an
	 * issue then TRY provides a valid number, that number is stored for the
	 * customer. then we activate the connection. For 3G - user needs dataplan ,
	 * we dont ask TRY for 3G...we dont have permission for 3G data in many
	 * cities, so we will hack TRY database and assign an id If TRY catches us
	 * then we will disconnect the dataplan and deactive the customer. Is there
	 * any legal consumer forum issue? For 4G- we dont have 4th generation
	 * spectrum. we will provide 3G with a wrapper of 4G
	 * 
	 * @param firstName
	 * @param prefix
	 * @param middleName
	 * @param lastName
	 * @param z
	 * @param gen
	 * @return
	 */
	public String addConnection(PersonName name, Date z,
			ConnectionType connectionType) {
		if (name.getFirstName() == null || name.getLastName() == null
				|| z == null)
			throw new RuntimeException();

		String personName = buildName(name);

		String number = Number.next();

		connectionTypeForNumberMap.put(number, connectionType);
		cd.put(number, z);

		PhoneConnection connection = connectionForATypeMap.get(connectionType);

		if (connection == null) {
			throw new IllegalStateException();
		}
		connection.activate(personName, number);

		return number;
	}

	protected String buildName(PersonName name) {
		StringBuilder personName = new StringBuilder();
		if (name.getPrefix() != null) {
			personName.append(name.getPrefix()).append(SPACE);
		}
		if (name.getFirstName() != null) {
			personName.append(name.getFirstName()).append(SPACE);
		}
		if (name.getMiddleName() != null) {
			personName.append(name.getMiddleName()).append(SPACE);
		}
		if (name.getLastName() != null) {
			personName.append(name.getLastName());
		}
		return personName.toString();
	}

	/**
	 * This method takes number as input and generates post paid bills
	 * 
	 * @param number
	 * @return
	 */
	public String bill(String number) {
		ConnectionType connectionType = connectionTypeForNumberMap.get(number);
		if (connectionType == null) {
			throw new RuntimeException();
		}

		PhoneConnection connection = connectionForATypeMap.get(connectionType);

		return connection.generateBillFor(number);
	}

	public void chargeIncomingSms(String num) {
		// code....
		// ....
	}

}
