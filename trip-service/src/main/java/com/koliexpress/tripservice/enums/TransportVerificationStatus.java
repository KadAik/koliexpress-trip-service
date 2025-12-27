package com.koliexpress.tripservice.enums;

public enum TransportVerificationStatus {
    PENDING,        // Waiting to be checked
    VERIFIED,       // Checked by API or manually
    REJECTED,       // Info not valid
    MANUAL_CHECK    // Require manual check
}
