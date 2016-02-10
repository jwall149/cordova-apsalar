#import <Cordova/CDV.h>
#import "Apsalar.h"

@interface CDVApsalarPlugin: CDVPlugin

- (void)sendEvent: (CDVInvokedUrlCommand*)command;

@end

