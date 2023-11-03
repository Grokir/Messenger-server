from http.server import HTTPServer, BaseHTTPRequestHandler
import json
import cgi
import base64

from cryptography.hazmat.primitives.asymmetric.rsa import rsa_recover_prime_factors

import primary_encryption as primary
import rsa_encryption as rsa

from source import RECEIVER_HOST
from source import RECEIVER_PORT



class HTTPHandler(BaseHTTPRequestHandler):
        
    __rsa_private_key,__rsa_public_key = rsa.generate_key_pair()

    def __rsa_decrypt(self, enc_msg):
        return rsa.rsa_decrypt(enc_msg, self.__rsa_private_key)

    def set_response(self):
        self.send_response(200)
        self.send_header("Content-type", "text/html")
        self.end_headers()
        
    def do_GET(self):
        print("\n\n[GET]")
        
        # sender_host = 
        # print(self.headers["Host"])
        
        if self.headers['content-type']:

            ctype, pdict = cgi.parse_header(self.headers['content-type'])
            message = ""
            if ctype == 'application/json':
                # self.send_response(400)
                # self.end_headers()
                  
                # read the message and convert it into a python dictionary
                length = int(self.headers['content-length'])
                message = json.loads(self.rfile.read(length))
                print(message)

        self.set_response()    

        # rsa.print_public_key(self.__rsa_public_key)
        # self.wfile.write(bytes(self.__rsa_public_key.decode(), "utf8"))
        self.wfile.write(self.__rsa_public_key)
        
        
    def do_POST(self):
        print("\n\n[POST]")
        
        print(self.headers['content-type'])
        request_data = self.headers['Authorization'].split()
        # enc_login, enc_pass = base64.b64decode(request_data[0]).decode('ascii').split('&')
        print(self.__rsa_decrypt(base64.b64decode(request_data[0])))        

        # print(f"login={self.__rsa_decrypt(enc_login)}, pass={self.__rsa_decrypt(enc_pass)}")
        
        ctype, pdict = cgi.parse_header(self.headers['content-type'])
        message = ""
        #refuse to receive non-json content
        if ctype == 'application/json':
            # self.send_response(400)
            # self.end_headers()
                  
            # read the message and convert it into a python dictionary
            length = int(self.headers['content-length'])
            message = json.loads(self.rfile.read(length))
        
        # add a property to the object, just to mess with data
        # message['received'] = 'ok'
        
        # send the message back
        self.set_response()  
        # print(message)
        self.wfile.write(bytes("received:ok", 'utf8'))
        
        
        
        
        


if __name__ == "__main__":
    server = HTTPServer((RECEIVER_HOST, RECEIVER_PORT), HTTPHandler)
    print("Server now running...")

    server.serve_forever()
    server.server_close()
    print("Server stopped!")

            

