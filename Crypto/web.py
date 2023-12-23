from asyncio.windows_events import NULL
from http.server import HTTPServer, BaseHTTPRequestHandler
import json
import cgi

import base64
import primary_encryption as pEnc

import source


class HTTPHandler(BaseHTTPRequestHandler):

    def __set_response(self):
        self.send_response(200)
        self.send_header("Content-type", "text/html")
        self.end_headers()
        
    def __set_response_JSON(self, dataJSON):
        self.send_response(200)
        self.send_header('Content-Type', 'application/json')
        self.end_headers()
        self.wfile.write(bytes(json.dumps(dataJSON), 'utf-8'))


    # GET запрос для дешифровки
    def do_GET(self):
        print("\n\n[GET]")
        # print("\n\n[POST]")
                    
        if self.headers['content-type']:

            ctype, pdict = cgi.parse_header(self.headers['content-type'])
            message = {}
            if ctype == 'application/json':
                # self.send_response(400)
                # self.end_headers()
                  
                # read the message and convert it into a python dictionary
                length = int(self.headers['content-length'])
                message = json.loads(self.rfile.read(length))
                # print(message)
                for key in message:
                    if key != source.LOGIN_JSON_KEY and key != source.PASSWORD_JSON_KEY and key!= source.MSG_TEXT_JSON_KEY:
                        continue
                    
                    value =  str(base64.b64decode(message[key]).decode('ascii'))
                    message[key] = pEnc.primary_decrypt(value)
                print(f"[{key}]: {message[key]}")

            self.__set_response_JSON(message)


        
    # POST запрос для шифрования        
    def do_POST(self):
        print("\n\n[POST]")
        if self.headers['content-type']:

            ctype, pdict = cgi.parse_header(self.headers['content-type'])
            message = {}
            if ctype == 'application/json':
                # self.send_response(400)
                # self.end_headers()
                  
                # read the message and convert it into a python dictionary
                length = int(self.headers['content-length'])
                message = json.loads(self.rfile.read(length))
                # print(message)
                print(message)
                flag = False
                for key in message:
                    if source.LOGIN_JSON_KEY and source.PASSWORD_JSON_KEY and source.MSG_TEXT_JSON_KEY and source.IS_DECRYPT not in message:
                        break

                    if key == source.IS_DECRYPT:
                        flag = message[key]
                        continue
                    
                    value = ""
                    if flag:
                        base64_decode =  str(base64.b64decode(message[key]).decode('ascii'))
                        value = pEnc.primary_decrypt(base64_decode)
                    else:
                        base64_bytes = base64.b64encode(pEnc.primary_encrypt(message[key]).encode('ascii'))
                        value = base64_bytes.decode('ascii')
                    
                    message[key] = value

                print(f"[{key}]: {message[key]}")

            self.__set_response_JSON(message)
        
        
        
        
        


if __name__ == "__main__":
    server = HTTPServer((source.RECEIVER_HOST, source.RECEIVER_PORT), HTTPHandler)
    print("Server now running...")

    server.serve_forever()
    server.server_close()
    print("Server stopped!")

            

