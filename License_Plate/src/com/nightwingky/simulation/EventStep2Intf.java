package com.nightwingky.simulation;

import com.nightwingky.vo.CustomerVO;

/**
 * Created by nightwingky on 17-3-31.
 */
public interface EventStep2Intf {

    void arrivalStep2(CustomerVO c);

    void departureStep2();

    void onServiceStep2(CustomerVO c);
}
