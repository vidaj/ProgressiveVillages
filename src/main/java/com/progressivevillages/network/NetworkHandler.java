package com.progressivevillages.network;

import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class NetworkHandler {
	
	private SimpleNetworkWrapper network;

	public NetworkHandler(SimpleNetworkWrapper network) {
		this.network = network;
	}
	
	public void init() {
		registerServerPackets();
		registerClientPackets();
	}

	private void registerServerPackets() {
		
	}
	
	private void registerClientPackets() {
		
	}
}
