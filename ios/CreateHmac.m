#import "CreateHmac.h"
#import "NSString+Utils.h"


@implementation CreateHmac

RCT_EXPORT_MODULE()

RCT_REMAP_METHOD(createHmacSHA1, secret:(NSString *)secret data:(NSDictionary *)data createHmacWithResolver:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject) {
    resolve([self signData:data secretKey:secret]);
}

- (NSString*)signData:(NSDictionary *)data
                                  secretKey: (NSString*)_secretKey {
    NSError *error;
    NSData *jsonData = [NSJSONSerialization dataWithJSONObject:data
                                                       options:0
                                                         error:&error];
    
    if (!jsonData) {
        NSLog(@"Got an error: %@", error);
        return nil;
    } else {
        NSString *hash = [[NSString alloc] initWithData:jsonData encoding:NSUTF8StringEncoding];
        return [hash signHmacSHA1WithKey:_secretKey];
    }
}

@end
