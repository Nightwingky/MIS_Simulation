package com.nightwingky.simulation;

import com.nightwingky.vo.CustomerVO;

/**
 * Created by nightwingky on 17-3-30.
 */
public interface EventStep1Intf {

    void queueStep1Start();

    void arrivalStep1();

    void departureStep1();

    void onServiceStep1(CustomerVO c);
}
