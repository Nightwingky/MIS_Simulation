package com.nightwingky.simulation;

import com.nightwingky.vo.CustomerVO;
import com.nightwingky.vo.DiscreteEventVO;

/**
 * Created by nightwingky on 17-3-31.
 */
public interface EventStep2Intf {

    void arrivalStep2(DiscreteEventVO event, CustomerVO c);

    void departureStep2();

    void onServiceStep2(CustomerVO c);
}
