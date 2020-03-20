#import "CreateHmac.h"
#import "NSString+Utils.h"


@implementation CreateHmac

RCT_EXPORT_MODULE()

RCT_REMAP_METHOD(createHmacSHA1, secret:(NSString *)secret data:(NSString *)data createHmacWithResolver:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject) {
    resolve([self signData:data secretKey:secret]);
}

- (NSString*)signData:(NSString *)data
                                  secretKey: (NSString*)_secretKey {
    return [data signHmacSHA1WithKey:_secretKey];
}

@end


